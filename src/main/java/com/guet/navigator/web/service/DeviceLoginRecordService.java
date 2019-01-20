package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.LoginRecord;

public interface DeviceLoginRecordService {

     LoginRecord findByDeviceId(String deviceId);

     Boolean createDeviceRecord(LoginRecord deviceRecord);

}
