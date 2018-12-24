package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.User;

public interface UserService {

     boolean accountRegistered(String account);

     void createUser(User user);

     User findByUserAccount(String account);

     User findByUserId(String userId);

     boolean updateUser(User user);

}
