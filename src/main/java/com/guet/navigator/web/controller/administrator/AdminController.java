package com.guet.navigator.web.controller.administrator;

import com.guet.navigator.web.constant.Messages;
import com.guet.navigator.web.constant.administrator.AdministratorConstant;
import com.guet.navigator.web.pojo.Administrator;
import com.guet.navigator.web.service.AdministratorService;
import com.guet.navigator.web.vo.LoginMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Administrator
 * @date 9/10/2018
 */
@Controller
@RequestMapping("/administrator")
public class AdminController {

    @Autowired
    private AdministratorService administratorService;

    /**
     * 验证登录信息
     *
     * @param administrator
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public LoginMessageVo loginValidate(@RequestBody Administrator administrator, HttpServletRequest request,
                                        HttpServletResponse response, HttpSession session) {

        //判断administrator是否为null
        if (!StringUtils.isEmpty(administrator)) {

            //用户输入的账号
            String account = administrator.getUsername();
            //用户输入的密码
            String password = administrator.getPassword();

            //判断账号，密码是否为空
            if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
                //返回账户或密码为空的错误信息
                return LoginMessageVo.loginFailMessage(Messages.USERNAME_OR_PASSWORD_EMPTY);
            } else {
                //从数据中查询管理员账号
                Administrator admin = administratorService.findByUserAccount(account);
                if (StringUtils.isEmpty(admin)) {
                    //返回账户不存在的错误信息
                    return LoginMessageVo.loginFailMessage(Messages.ACCOUNT_NOT_EXIST);
                } else {
                    if (password.equals(admin.getPassword())) {
                        //清除密码
                        admin.setPassword("");
                        session.setAttribute(AdministratorConstant.ADMINISTRATOR, admin);
                        return LoginMessageVo.loginSuccessMessage(Messages.LOGIN_SUCCESS);
                    } else {
                        //返回密码错误信息
                        return LoginMessageVo.loginFailMessage(Messages.PASSWORD_ERROR);
                    }
                }
            }
        }

        //返回服务器内部错误
        return LoginMessageVo.loginFailMessage(Messages.SERVER_INNER_ERROR);
    }
}
