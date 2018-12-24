package com.guet.navigator.web.service;

import com.guet.navigator.web.pojo.Administrator;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 9/13/2018.
 */
public interface AdministratorService {

     Administrator findByUserAccount(String account);

}
