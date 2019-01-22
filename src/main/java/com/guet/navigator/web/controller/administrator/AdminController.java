package com.guet.navigator.web.controller.administrator;

import com.alibaba.fastjson.JSONObject;
import com.guet.navigator.web.constant.Messages;
import com.guet.navigator.web.constant.administrator.AdministratorConstant;
import com.guet.navigator.web.pojo.Administrator;
import com.guet.navigator.web.pojo.Device;
import com.guet.navigator.web.service.AdministratorService;
import com.guet.navigator.web.service.DeviceService;
import com.guet.navigator.web.utils.Page;
import com.guet.navigator.web.utils.PageData;
import com.guet.navigator.web.vo.LoginMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @date 9/10/2018
 */
@Controller
@RequestMapping("/administrator")
public class AdminController {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private DeviceService deviceService;

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
    @RequestMapping(value = "/infoDetails", method = RequestMethod.GET)
    public String adminDetails() {
        return AdministratorConstant.ADMIN_DETAILS;
    }

    /**
     * 设备管理界面
     *
     * @return
     */
    @RequestMapping(value = "/device/device-manage", method = RequestMethod.GET)
    public ModelAndView deviceManage(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {
        modelAndView.setViewName(AdministratorConstant.DEVICE_MANAGE);
        return modelAndView;
    }

    /**
     * 通过id查询device
     *
     * @param deviceId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/device/query/{deviceId}", method = RequestMethod.GET)
    @ResponseBody
    public Device getDeviceById(@PathVariable String deviceId, HttpServletRequest request, HttpServletResponse response) {
        Device device = deviceService.getByDeviceId(deviceId);
        return device;
    }

    /**
     * 新增设备信息
     *
     * @param device
     * @return
     */
    @RequestMapping(value = "/device/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addDevice(@RequestBody Device device) {
        Map<String, Object> msg = new HashMap<String, Object>();
        if (StringUtils.isEmpty(device.getCdKey()) || StringUtils.isEmpty(device.getCreateTime()) ||
                StringUtils.isEmpty(device.getDeviceId()) || StringUtils.isEmpty(device.getDeviceName()) ||
                StringUtils.isEmpty(device.getDeviceVersion())) {
            //存在空项
            msg.put("statusCode", 300);
        } else {
            if (deviceService.saveDevice(device)) {
                //保存成功
                msg.put("statusCode", 200);
                msg.put("device", device);
            } else {
                //服务器内部错误
                msg.put("statusCode", 500);
            }
        }
        return msg;
    }

    /**
     * 分页查询设备记录
     *
     * @return
     */
    @RequestMapping(value = "/device/query", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryDevice(HttpServletRequest request, HttpServletResponse response) {
        PageData<Device> pageData = new PageData<Device>();
        Page<Device> page = pageData.requestToPage(request);
        page.setAaData(deviceService.listDeviceLimit(page.getiDisplayStart(), page.getiDisplayLength()));
        JSONObject msg = new JSONObject();
        msg.put("sEcho", page.getsEcho());
        msg.put("iTotalRecords", page.getiDisplayLength());
        msg.put("iTotalDisplayRecords", deviceService.listAllDevice().size());
        msg.put("aaData", page.getAaData());
        return msg;
    }

    /**
     * 验证登录信息
     *
     * @param administrator
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginValidate", method = RequestMethod.POST)
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
