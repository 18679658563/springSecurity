package com.rz.security.filter;

import com.rz.security.dto.LoginUser;
import com.rz.security.service.ITokenService;
import com.rz.security.service.impl.CustomUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description: Token过滤器
 * User: silence
 * Date: 2019-01-29
 * Time: 下午3:45
 */
@Component
public class TokenFilter extends OncePerRequestFilter {

    public static final String TOKEN_KEY = "token";

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private CustomUserService customUserService;

    private static final Long MINUTES_10 = 10 * 60 * 1000L;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(httpServletRequest);
        if(StringUtils.isNotBlank(token)){
            LoginUser loginUser = tokenService.getLoginUser(token);
            if(loginUser != null){
                loginUser = checkLoginTime(loginUser);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    /**
     * 校验时间
     * 过期时间与当前时间对比，临近过期十分钟内的话，自动刷新缓存
     * @param loginUser
     * @return
     */
    private LoginUser checkLoginTime(LoginUser loginUser){
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if(expireTime - currentTime <= MINUTES_10){
            String token =loginUser.getToken();
            loginUser = (LoginUser)customUserService.loadUserByUsername(loginUser.getUsername());
            loginUser.setToken(token);
            tokenService.refresh(loginUser);
        }
        return loginUser;
    }

    public static String getToken(HttpServletRequest request){
        String token = request.getParameter(TOKEN_KEY);
        if(StringUtils.isBlank(token)){
            token = request.getHeader(TOKEN_KEY);
        }
        return token;
    }

}
