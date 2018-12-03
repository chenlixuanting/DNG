package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.User;

public interface UserDao {

    public User findByUserAccount(String account);

    public void saveUser(User user);

    public User findByUserId(String userId);

    public boolean updateUser(User user);

}
