package com.rz.security.controller;

import com.rz.security.tools.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: silence
 * Date: 2018-12-24
 * Time: 上午11:08
 */
@Controller
public class HomeController {


    @RequestMapping("/")
    public String index(Model model){
        Result msg =  new Result("200",null,"123");
        model.addAttribute("result", msg);
        return "home";
    }
}
