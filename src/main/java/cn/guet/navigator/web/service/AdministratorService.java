package cn.guet.navigator.web.service;

import cn.guet.navigator.web.pojo.Administrator;

/**
 * Created by Administrator on 9/13/2018.
 */
public interface AdministratorService {

     Administrator findByUserAccount(String account);

}
