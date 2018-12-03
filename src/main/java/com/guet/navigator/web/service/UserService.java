package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.User;

public interface UserService {

    public boolean accountRegistered(String account);

    public void createUser(User user);

    public User findByUserAccount(String account);

    public User findByUserId(String userId);

    public boolean updateUser(User user);

}
