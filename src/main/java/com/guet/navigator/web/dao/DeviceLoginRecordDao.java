package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.DeviceLoginRecord;

/**
 * @author Administrator
 */
public interface DeviceLoginRecordDao {

    DeviceLoginRecord findByDeviceId(String deviceId);

    Boolean saveDeviceRecord(DeviceLoginRecord deviceRecord);

}
