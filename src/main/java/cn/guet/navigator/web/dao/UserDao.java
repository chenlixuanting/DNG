package cn.guet.navigator.web.dao;

import cn.guet.navigator.web.pojo.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserDao {

    User getByUserAccount(String account);

    void saveUser(User user);

    User getByUserId(String userId);

    boolean updateUser(User user);

    List<User> listUserLimit(int start, int end);

    Long countAllUser();

    Boolean delete(User user);

}
