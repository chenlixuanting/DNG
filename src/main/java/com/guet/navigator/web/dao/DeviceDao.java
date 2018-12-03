package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.Device;

public interface DeviceDao {

    public Boolean save(Device device);

    public Device findByDeviceId(String deviceId);
}
