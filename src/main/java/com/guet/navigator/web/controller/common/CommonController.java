package com.guet.navigator.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommonController {

    /**
     * 当访问 /,/index.html,/index 则跳转到login界面
     * @return
     */
    @RequestMapping(value = {"/","/index.html","/index"})
    public String webRootIndex(HttpServletRequest request){

        HttpSession session = request.getSession();

        return "redirect:administrator/login";
    }

}
