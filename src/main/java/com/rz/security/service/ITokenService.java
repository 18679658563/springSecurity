package com.rz.security.service;

import com.rz.security.dto.LoginUser;
import com.rz.security.dto.TokenDto;

/**
 * Created with IntelliJ IDEA.
 * Description: token 管理器
 *              可存到数据库和redis中，具体看实现类
 *              默认基于redis，实现类为TokenServiceJWTImpl
 *              若存于数据库，在TokenServiceJWTImpl嘞上的朱姐@Primary挪动到TokenServiceDbImpl即可
 * User: silence
 * Date: 2019-01-29
 * Time: 下午3:54
 */
public interface ITokenService {

    TokenDto saveToken(LoginUser loginUser);

    void refresh(LoginUser loginUser);

    LoginUser getLoginUser(String token);

    boolean deleteToken(String token);
}
