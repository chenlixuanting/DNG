package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.dao.AdministratorDao;
import com.guet.navigator.web.pojo.Administrator;
import com.guet.navigator.web.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 9/13/2018.
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorDao administratorDao;

    @Override
    public Administrator findByUserAccount(String account) {
        return administratorDao.findByUserAccount(account);
    }


}
