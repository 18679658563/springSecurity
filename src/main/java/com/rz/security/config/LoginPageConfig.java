package com.rz.security.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2019-01-29
 * Time: 下午3:38
 */
@Controller
public class LoginPageConfig {


    @RequestMapping("/")
    public RedirectView loginPage(){
        return new RedirectView("/login.html");
    }
}
