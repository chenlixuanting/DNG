package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.dao.DeviceDao;
import com.guet.navigator.web.pojo.Device;
import com.guet.navigator.web.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private DeviceDao deviceDao;

    public Boolean createDevice(Device device){
        return deviceDao.save(device);
    }

    @Override
    public Device findByDeviceId(String deviceId) {
        return deviceDao.findByDeviceId(deviceId);
    }

}
