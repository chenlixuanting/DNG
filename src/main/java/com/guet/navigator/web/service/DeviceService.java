package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.Device;

import java.util.List;

/**
 * @author Administrator
 */
public interface DeviceService {

    /**
     * 保存一个新的设备
     *
     * @param device
     * @return
     */
    Boolean saveDevice(Device device);

    /**
     * 通过deviceId获取设备
     *
     * @param deviceId
     * @return
     */
    Device getByDeviceId(String deviceId);

    /**
     * 获取全部设备记录
     *
     * @return
     */
    List<Device> listAllDevice();

    /**
     * 分页查询
     *
     * @param start
     * @param length
     * @return
     */
    List<Device> listDeviceLimit(int start, int length);

    /**
     * 通过ID删除设备记录
     *
     * @param deviceId
     * @return
     */
    Boolean delDeviceById(String deviceId);
}
