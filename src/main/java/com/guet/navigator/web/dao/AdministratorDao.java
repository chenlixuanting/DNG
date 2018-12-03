package com.guet.navigator.web.dao;


import com.guet.navigator.web.pojo.Administrator;

/**
 * Created by Administrator on 9/13/2018.
 */
public interface AdministratorDao {

    public Administrator findByUserAccount(String userAccount);

}
