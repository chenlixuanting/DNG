package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.dao.UserDao;
import com.guet.navigator.web.pojo.User;
import com.guet.navigator.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    /**
     * 判断用户名是否已经被注册
     *
     * @param account
     * @return
     */
    public boolean accountRegistered(String account){

        User user = userDao.findByUserAccount(account);

        if(!StringUtils.isEmpty(user)){
            return true;
        }

        return false;

    }

    @Override
    public void createUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User findByUserAccount(String account) {
        return userDao.findByUserAccount(account);
    }

    @Override
    public User findByUserId(String userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

}
