package com.guet.navigator.web.controller.administrator;

import com.alibaba.fastjson.JSONObject;
import com.guet.navigator.web.pojo.Device;
import com.guet.navigator.web.service.DeviceService;
import com.guet.navigator.web.utils.Page;
import com.guet.navigator.web.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员设备控制器
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/administrator/device")
public class AdminDeviceController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 更新设备记录
     *
     * @param device
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateDevice(@RequestBody Device device) {

        Map<String, Object> msg = new HashMap<String, Object>();

        if (StringUtils.isEmpty(device) || StringUtils.isEmpty(device.getDeviceVersion()) ||
                StringUtils.isEmpty(device.getDeviceName()) || StringUtils.isEmpty(device.getDeviceId()) ||
                StringUtils.isEmpty(device.getCreateTime()) || StringUtils.isEmpty(device.getCdKey())) {
            //数据存在空项
            msg.put("statusCode", 300);
        } else {
            Device d = deviceService.getByDeviceId(device.getDeviceId());
            if (!StringUtils.isEmpty(d)) {
                d.setDeviceVersion(device.getDeviceVersion());
                d.setDeviceName(device.getDeviceName());
                d.setCdKey(device.getCdKey());
                if (deviceService.updateDevice(d)) {
                    //记录更新成功
                    msg.put("statusCode", 200);
                } else {
                    //服务器内部错误
                    msg.put("statusCode", 500);
                }
            } else {
                //设备记录不存在
                msg.put("statusCode", 404);
            }
        }
        return msg;
    }

    /**
     * 通过id查询device
     *
     * @param deviceId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/get/{deviceId}", method = RequestMethod.GET)
    @ResponseBody
    public Device getDeviceById(@PathVariable String deviceId, HttpServletRequest request, HttpServletResponse response) {
        Device device = deviceService.getByDeviceId(deviceId);
        return device;
    }

    /**
     * 通过设备ID删除设备记录
     *
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/del/{deviceId}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delDeviceById(@PathVariable String deviceId) {
        Map<String, Object> msg = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(deviceId)) {
            Device device = deviceService.getByDeviceId(deviceId);
            if (!StringUtils.isEmpty(device)) {
                if (deviceService.delDevice(device)) {
                    //删除成功
                    msg.put("statusCode", 200);
                } else {
                    //服务器内部错误
                    msg.put("statusCode", 500);
                }
            } else {
                //要删除的设备记录不存在
                msg.put("statusCode", 300);
            }
        } else {
            //非法用户
            msg.put("statusCode", 404);
        }
        return msg;
    }

    /**
     * 新增设备记录
     *
     * @param device
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
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
    @RequestMapping(value = "/query", method = RequestMethod.GET)
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

}
