package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.DeviceRecord;

/**
 * @author Administrator
 */
public interface DeviceRecordDao {

    DeviceRecord findByDeviceId(String deviceId);

    Boolean saveDeviceRecord(DeviceRecord deviceRecord);

}
