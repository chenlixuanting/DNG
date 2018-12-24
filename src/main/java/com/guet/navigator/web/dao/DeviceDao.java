package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.Device;

public interface DeviceDao {

     Boolean save(Device device);

     Device findByDeviceId(String deviceId);
}
