package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.DeviceRecord;

public interface DeviceRecordDao {

    public DeviceRecord findByDeviceId(String deviceId);

    public Boolean saveDeviceRecord(DeviceRecord deviceRecord);

}
