package com.guet.navigator.web.dao;

import com.guet.navigator.web.pojo.User;

public interface UserDao {

     User findByUserAccount(String account);

     void saveUser(User user);

     User findByUserId(String userId);

     boolean updateUser(User user);

}
