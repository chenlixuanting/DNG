package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.DeviceLoginRecord;

public interface DeviceLoginRecordService {

     DeviceLoginRecord findByDeviceId(String deviceId);

     Boolean createDeviceRecord(DeviceLoginRecord deviceRecord);

}
