package cn.guet.navigator.web.controller.administrator;

import cn.guet.navigator.web.pojo.User;
import cn.guet.navigator.web.service.UserService;
import cn.guet.navigator.web.utils.Page;
import cn.guet.navigator.web.utils.PageData;
import com.alibaba.fastjson.JSONObject;
import cn.guet.navigator.web.constant.common.CommonConstant;
import cn.guet.navigator.web.constant.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员用户控制器
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/administrator/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delUser(@PathVariable String userId) {
        Map<String, Object> msg = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(userId)) {
            User u = userService.findByUserId(userId);
            if (!StringUtils.isEmpty(u)) {
                if(userService.delUser(u)){
                    msg.put(CommonConstant.STATUS_CODE, StatusCode.SUCCESS);
                }else{
                    msg.put(CommonConstant.STATUS_CODE,StatusCode.SERVER_INNER_ERROR);
                }
            } else {
                //账户不存在
                msg.put(CommonConstant.STATUS_CODE, StatusCode.ACCOUNT_NOT_EXIST);
            }
        } else {
            //服务器内部错误
            msg.put(CommonConstant.STATUS_CODE, StatusCode.SERVER_INNER_ERROR);
        }
        return msg;
    }

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject queryUser(HttpServletRequest request, HttpServletResponse response) {
        PageData<User> pageData = new PageData<User>();
        Page<User> page = pageData.requestToPage(request);
        page.setAaData(userService.listUserLimit(page.getiDisplayStart(), page.getiDisplayLength()));
        JSONObject msg = new JSONObject();
        msg.put("sEcho", page.getsEcho());
        msg.put("iTotalRecords", page.getiDisplayLength());
        msg.put("iTotalDisplayRecords", userService.countAllUser());
        msg.put("aaData", page.getAaData());
        return msg;
    }

    /**
     * 通过userId获取用户实体
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserById(@PathVariable String userId) {
        Map<String, Object> msg = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(userId)) {
            User user = userService.findByUserId(userId);
            if (!StringUtils.isEmpty(user)) {
                //成功
                msg.put(CommonConstant.STATUS_CODE, StatusCode.SUCCESS);
                msg.put("user", user);
            } else {
                //记录不存在
                msg.put(CommonConstant.STATUS_CODE, StatusCode.ACCOUNT_NOT_EXIST);
            }
        } else {
            //服务内部错误
            msg.put(CommonConstant.STATUS_CODE, StatusCode.SERVER_INNER_ERROR);
        }
        return msg;
    }

}
