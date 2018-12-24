package com.guet.navigator.web.dao;


import com.guet.navigator.web.pojo.Administrator;

/**
 *
 * @author Administrator
 * @date 9/13/2018
 */
public interface AdministratorDao {

    Administrator findByUserAccount(String userAccount);

}
