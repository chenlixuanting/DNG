package com.guet.navigator.web.controller.administrator;

import com.guet.navigator.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员用户控制器
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/administrator/user")
public class UserController {

    @Autowired
    private UserService userService;

}
