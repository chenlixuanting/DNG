package cn.guet.navigator.web.service;

import cn.guet.navigator.web.pojo.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface UserService {

    /**
     * @param account
     * @return
     */
    boolean accountRegistered(String account);

    /**
     * @param user
     */
    void createUser(User user);

    /**
     * @param account
     * @return
     */
    User findByUserAccount(String account);

    /**
     * @param userId
     * @return
     */
    User findByUserId(String userId);

    /**
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 分页获取用户信息
     */
    List<User> listUserLimit(int start, int end);

    /**
     * 统计用户数量
     *
     * @return
     */
    Long countAllUser();

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    Boolean delUser(User user);

}
