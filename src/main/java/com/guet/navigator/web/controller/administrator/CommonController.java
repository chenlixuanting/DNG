package com.guet.navigator.web.controller.administrator;

import com.guet.navigator.web.constant.administrator.AdministratorConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/administrator")
public class CommonController {

    /**
     * 管理员登录界面
     *
     * @param session
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session, HttpServletResponse response) {
        //跳转到登陆页面
        return AdministratorConstant.ADMIN_LOGIN;
    }

    /**
     * 设备管理界面
     *
     * @return
     */
    @RequestMapping(value = "/device-manage", method = RequestMethod.GET)
    public ModelAndView deviceManage(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        modelAndView.setViewName(AdministratorConstant.DEVICE_MANAGE);
        return modelAndView;
    }

    /**
     * 管理员首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return AdministratorConstant.ADMIN_INDEX;
    }

    /**
     * 提示信息页面
     *
     * @return
     */
    @RequestMapping(value = "/form-notifications", method = RequestMethod.GET)
    public String notifications() {
        return null;
    }

    /**
     * 管理员详细信息页面
     *
     * @return
     */
    @RequestMapping(value = "/info-detail", method = RequestMethod.GET)
    public String adminDetails() {
        return AdministratorConstant.ADMIN_DETAILS;
    }

}
