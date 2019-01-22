package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.dao.DeviceDao;
import com.guet.navigator.web.pojo.Device;
import com.guet.navigator.web.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Override
    public Boolean saveDevice(Device device) {
        return deviceDao.save(device);
    }

    @Override
    public Device getByDeviceId(String deviceId) {
        return deviceDao.getByDeviceId(deviceId);
    }

    @Override
    public List<Device> listAllDevice() {
        return deviceDao.listAllDevice();
    }

    @Override
    public List<Device> listDeviceLimit(int start, int length) {
        return deviceDao.listDeviceLimit(start, length);
    }

    @Override
    public Boolean delDeviceById(String deviceId) {
        return deviceDao.delDeviceById(deviceId);
    }

    @Override
    public Boolean delDevice(Device device) {
        return deviceDao.delDevice(device);
    }

}
