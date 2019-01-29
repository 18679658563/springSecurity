package com.rz.security.config;

import com.rz.security.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-29
 * Time: 下午5:14
 */
@Configuration
public class SecurityHandlerConfig {

    @Autowired
    private ITokenService tokenService;


}
