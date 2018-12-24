package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.Device;

public interface DeviceService {

     Boolean createDevice(Device device);

     Device findByDeviceId(String deviceId);
}
