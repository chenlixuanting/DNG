package cn.guet.navigator.web.service.impl;

import cn.guet.navigator.web.dao.AdministratorDao;
import cn.guet.navigator.web.pojo.Administrator;
import cn.guet.navigator.web.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Administrator
 * @date 9/13/2018
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
