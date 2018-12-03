package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.Device;

public interface DeviceService {

    public Boolean createDevice(Device device);

    public Device findByDeviceId(String deviceId);
}
