package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.LoginRecord;

/**
 * @author Administrator
 */
public interface DeviceLoginRecordDao {

    LoginRecord findByDeviceId(String deviceId);

    Boolean saveDeviceRecord(LoginRecord deviceRecord);

}
