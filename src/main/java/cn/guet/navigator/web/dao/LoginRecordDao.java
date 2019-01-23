package cn.guet.navigator.web.dao;

import cn.guet.navigator.web.pojo.LoginRecord;

/**
 * @author Administrator
 */
public interface LoginRecordDao {

    LoginRecord getByDeviceId(String deviceId);

    Boolean saveDeviceRecord(LoginRecord deviceRecord);

    LoginRecord getByUserId(String userId);

}
