package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.DeviceLoginRecord;

public interface DeviceLoginRecordService {

    public DeviceLoginRecord findByDeviceId(String deviceId);

    public Boolean createDeviceRecord(DeviceLoginRecord deviceRecord);

}
