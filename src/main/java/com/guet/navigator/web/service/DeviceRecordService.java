package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.DeviceRecord;

public interface DeviceRecordService {

    public DeviceRecord findByDeviceId(String deviceId);

    public Boolean createDeviceRecord(DeviceRecord deviceRecord);

}
