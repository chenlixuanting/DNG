package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.dao.DeviceRecordDao;
import com.guet.navigator.web.pojo.DeviceRecord;
import com.guet.navigator.web.service.DeviceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceRecordServiceImpl implements DeviceRecordService{

    @Autowired
    private DeviceRecordDao deviceRecordDao;

    @Override
    public DeviceRecord findByDeviceId(String deviceId) {
        return deviceRecordDao.findByDeviceId(deviceId);
    }

    @Override
    public Boolean createDeviceRecord(DeviceRecord deviceRecord) {
        return deviceRecordDao.saveDeviceRecord(deviceRecord);
    }

}
