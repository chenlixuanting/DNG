package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.User;

/**
 * @author Administrator
 */
public interface UserService {

     /**
      *
      * @param account
      * @return
      */
     boolean accountRegistered(String account);

     /**
      *
      * @param user
      */
     void createUser(User user);

     /**
      *
      * @param account
      * @return
      */
     User findByUserAccount(String account);

     /**
      *
      * @param userId
      * @return
      */
     User findByUserId(String userId);

     /**
      *
      * @param user
      * @return
      */
     boolean updateUser(User user);

}
