package com.rz.security.service.impl;

import com.rz.security.dto.LoginUser;
import com.rz.security.dto.TokenDto;
import com.rz.security.pojo.Log;
import com.rz.security.service.ILogService;
import com.rz.security.service.ITokenService;
import com.rz.security.tools.SerializeUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description: jwt实现的token, token存到redis的实现类
 * User: silence
 * Date: 2019-01-30
 * Time: 上午11:17
 */
@Service
@Primary
public class TokenServiceJWTImpl implements ITokenService {

    /**
     * token过期秒数
     */
    @Value("${token.expire.seconds}")
    private Integer expireSeconds;

    @Autowired
    private JedisPool pool;

    @Autowired
    private ILogService logService;

    //私钥
    @Value("${token.jwtSecret}")
    private String jwtSecret;

    private static Key KEY = null;

    private static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    /**
     * 保存token信息
     * @param loginUser
     * @return
     */
    @Override
    public TokenDto saveToken(LoginUser loginUser) {
        loginUser.setToken(UUID.randomUUID().toString());
        cacheLoginUser(loginUser);
        String jwtToken = createJWTToken(loginUser);
        Log log = new Log();
        log.setDescription("登陆");
        log.setType("INFO");
        logService.save(log);
        return new TokenDto(jwtToken,loginUser.getLoginTime());
    }

    /**
     * 更新缓存的用户信息
     * @param loginUser
     */
    @Override
    public void refresh(LoginUser loginUser) {
        cacheLoginUser(loginUser);
    }

    /**
     * 从缓存中获取登录用户的信息
     * @param token
     * @return
     */
    @Override
    public LoginUser getLoginUser(String token) {
        String uuid = getUUIDFromJWT(token);
        if(StringUtils.isNotEmpty(uuid)){
            Jedis jedis = null;
            try{
                jedis = pool.getResource();
                return (LoginUser)SerializeUtil.unserialize(jedis.get(getTokenKey(uuid).getBytes()));
            }finally {
                if(jedis != null){
                    jedis.close();
                }
            }

        }
        return null;
    }

    /**
     * 删除token
     * @param token
     * @return
     */
    @Override
    public boolean deleteToken(String token) {
        String uuid = getUUIDFromJWT(token);
        if(StringUtils.isNotEmpty(uuid)){
            Jedis jedis = null;
            try{
                jedis = pool.getResource();
                String key = getTokenKey(uuid);
                LoginUser loginUser = (LoginUser)SerializeUtil.unserialize(jedis.get(key.getBytes()));
                if(loginUser != null){
                    Log log = new Log();
                    log.setDescription("退出");
                    log.setType("INFO");
                    log.setUsername(loginUser.getUsername());
                    logService.save(log);
                    jedis.del(key.getBytes());
                    return true;
                }
            }finally{
                if(jedis != null){
                    jedis.close();
                }
            }


        }
        return false;
    }

    /**
     * 更新缓存公共方法
     * @param loginUser
     */
    private void cacheLoginUser(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);
        // 根据uuid将loginUser缓存
        String key = getTokenKey(loginUser.getToken());
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            byte[] b = jedis.get(key.getBytes());
            if(b != null && b.length > 0){
                jedis.del(key.getBytes());
            }
            System.out.println(key+"\n"+SerializeUtil.serialize(loginUser));
            jedis.set(key.getBytes(),SerializeUtil.serialize(loginUser));
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    /**
     * 设置token
     * @param uuid
     * @return
     */
    private String getTokenKey(String uuid) {
        return "tokens:" + uuid;
    }

    /**
     * 生成jwt
     * @param loginUser
     * @return
     */
    private String createJWTToken(LoginUser loginUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(LOGIN_USER_KEY, loginUser.getToken());// 放入一个随机字符串，通过该串可找到登陆用户
        String jwtToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
        return jwtToken;
    }

    /**
     * 生成KEY
     * @return
     */
    private Key getKeyInstance() {
        if (KEY == null) {
            synchronized (TokenServiceJWTImpl.class) {
                if (KEY == null) {// 双重锁
                    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
                    KEY = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
                }
            }
        }
        return KEY;
    }

    /**
     * 获得jwt加密后的uuid
     * @param jwtToken
     * @return
     */
    private String getUUIDFromJWT(String jwtToken) {
        if ("null".equals(jwtToken) || StringUtils.isBlank(jwtToken)) {
            return null;
        }
        try {
            Map<String, Object> jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwtToken).getBody();
            return MapUtils.getString(jwtClaims, LOGIN_USER_KEY);
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("token已过期");
        } catch (Exception e) {
        }
        return null;
    }

}
