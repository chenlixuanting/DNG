package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.dao.DeviceLoginRecordDao;
import com.guet.navigator.web.pojo.DeviceLoginRecord;
import com.guet.navigator.web.service.DeviceLoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class DeviceLoginRecordServiceImpl implements DeviceLoginRecordService {

    @Autowired
    private DeviceLoginRecordDao deviceRecordDao;

    @Override
    public DeviceLoginRecord findByDeviceId(String deviceId) {
        return deviceRecordDao.findByDeviceId(deviceId);
    }

    @Override
    public Boolean createDeviceRecord(DeviceLoginRecord deviceRecord) {
        return deviceRecordDao.saveDeviceRecord(deviceRecord);
    }

}
