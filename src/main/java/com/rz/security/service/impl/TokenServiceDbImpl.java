package com.rz.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rz.security.model.dto.LoginUser;
import com.rz.security.model.dto.TokenDto;
import com.rz.security.mapper.TokenMapper;
import com.rz.security.model.pojo.Token;
import com.rz.security.service.ITokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description: token存在数据库的实现类
 * User: silence
 * Date: 2019-01-30
 * Time: 上午11:17
 */
@Service
public class TokenServiceDbImpl implements ITokenService {

    /**
     * token过期秒数
     */
    @Value("${token.expire.seconds}")
    private Integer expireSeconds;
    @Autowired
    private TokenMapper tokenMapper;

    /**
     * 私钥
     */
    @Value("${token.jwtSecret}")
    private String jwtSecret;

    private static Key KEY = null;
    private static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    /**
     * 往数据库里存token
     * @param loginUser
     * @return
     */
    @Override
    public TokenDto saveToken(LoginUser loginUser) {
        loginUser.setToken(UUID.randomUUID().toString());
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);
        Token model = new Token();
        model.setId(loginUser.getToken());
        model.setCreateTime(new Date());
        model.setUpdateTime(new Date());
        model.setExpireTime(new Date(loginUser.getExpireTime()));
        model.setVal(JSONObject.toJSONString(loginUser));
        tokenMapper.insertToken(model);
        String jwtToken = createJWTToken(loginUser);
        return new TokenDto(jwtToken, loginUser.getLoginTime());
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
     * 更新数据库里的Token
     * @param loginUser
     */
    @Override
    public void refresh(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);
        Token model = tokenMapper.selectById(loginUser.getToken());
        model.setUpdateTime(new Date());
        model.setExpireTime(new Date(loginUser.getExpireTime()));
        model.setVal(JSONObject.toJSONString(loginUser));
        tokenMapper.updateToken(model);
    }

    /**
     * 根据token查询登录用户
     * @param jwtToken
     * @return
     */
    @Override
    public LoginUser getLoginUser(String jwtToken) {
        String uuid = getUUIDFromJWT(jwtToken);
        if (uuid != null) {
            Token model = tokenMapper.selectById(uuid);
            return toLoginUser(model);
        }
        return null;
    }

    /**
     * 删除Token
     * @param jwtToken
     * @return
     */
    @Override
    public boolean deleteToken(String jwtToken) {
        String uuid = getUUIDFromJWT(jwtToken);
        if (uuid != null) {
            Token model = tokenMapper.selectById(uuid);
            LoginUser loginUser = toLoginUser(model);
            if (loginUser != null) {
                tokenMapper.delete(uuid);
                return true;
            }
        }
        return false;
    }

    private LoginUser toLoginUser(Token model) {
        if (model == null) {
            return null;
        }
        // 校验是否已过期
        if (model.getExpireTime().getTime() > System.currentTimeMillis()) {
            return JSONObject.parseObject(model.getVal(), LoginUser.class);
        }

        return null;
    }

    private Key getKeyInstance() {
        if (KEY == null) {
            synchronized (TokenServiceDbImpl.class) {
                if (KEY == null) {// 双重锁
                    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
                    KEY = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
                }
            }
        }
        return KEY;
    }

    private String getUUIDFromJWT(String jwt) {
        if ("null".equals(jwt) || StringUtils.isBlank(jwt)) {
            return null;
        }
        Map<String, Object> jwtClaims = null;
        try {
            jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
            return MapUtils.getString(jwtClaims, LOGIN_USER_KEY);
        } catch (ExpiredJwtException e) {
        } catch (Exception e) {
        }
        return null;
    }
}
