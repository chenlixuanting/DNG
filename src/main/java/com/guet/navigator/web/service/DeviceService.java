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
     * 分页获取设备记录
     *
     * @param currentPage
     * @param size
     * @return
     */
    List<Device> listDeviceByPage(int currentPage, int size);

    /**
     * 获取全部设备记录
     *
     * @return
     */
    List<Device> listAllDevice();
}
