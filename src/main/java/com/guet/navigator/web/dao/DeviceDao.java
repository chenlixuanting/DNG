package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.Device;

import java.util.List;

/**
 * @author Administrator
 */
public interface DeviceDao {

    /**
     * 保存设备
     *
     * @param device
     * @return
     */
    Boolean save(Device device);

    /**
     * 通过设备Id获取设备记录
     *
     * @param deviceId
     * @return
     */
    Device getByDeviceId(String deviceId);

    /**
     * 获取所有设备记录
     *
     * @return
     */
    List<Device> listAllDevice();

    List<Device> listDeviceLimit(int start, int length);
}
