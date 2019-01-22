package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.dao.LoginRecordDao;
import com.guet.navigator.web.pojo.LoginRecord;
import com.guet.navigator.web.service.LoginRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class LoginRecordServiceImpl implements LoginRecordService {

    @Autowired
    private LoginRecordDao loginRecordDao;

    @Override
    public LoginRecord getByDeviceId(String deviceId) {
        return loginRecordDao.getByDeviceId(deviceId);
    }

    @Override
    public Boolean createDeviceRecord(LoginRecord deviceRecord) {
        return loginRecordDao.saveDeviceRecord(deviceRecord);
    }

    @Override
    public LoginRecord getByUserId(String userId) {
        return loginRecordDao.getByUserId(userId);
    }

}
