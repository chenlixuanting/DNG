package cn.guet.navigator.web.dao;


import cn.guet.navigator.web.pojo.Administrator;

/**
 *
 * @author Administrator
 * @date 9/13/2018
 */
public interface AdministratorDao {

    Administrator findByUserAccount(String userAccount);

}
