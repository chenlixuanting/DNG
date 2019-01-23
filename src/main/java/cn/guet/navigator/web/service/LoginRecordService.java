package cn.guet.navigator.web.service;

import cn.guet.navigator.web.pojo.LoginRecord;

/**
 * @author Administrator
 */
public interface LoginRecordService {

    /**
     * @param deviceId
     * @return
     */
    LoginRecord getByDeviceId(String deviceId);

    /**
     * @param deviceRecord
     * @return
     */
    Boolean createDeviceRecord(LoginRecord deviceRecord);

    /**
     * 通过用户Id获取登录记录
     *
     * @param userId
     * @return
     */
    LoginRecord getByUserId(String userId);

}
