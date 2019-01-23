package cn.guet.navigator.web.service.impl;

import cn.guet.navigator.web.pojo.User;
import cn.guet.navigator.web.service.UserService;
import cn.guet.navigator.web.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 判断用户名是否已经被注册
     *
     * @param account
     * @return
     */
    @Override
    public boolean accountRegistered(String account) {
        User user = userDao.getByUserAccount(account);
        if (!StringUtils.isEmpty(user)) {
            return true;
        }
        return false;
    }

    @Override
    public void createUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User findByUserAccount(String account) {
        return userDao.getByUserAccount(account);
    }

    @Override
    public User findByUserId(String userId) {
        return userDao.getByUserId(userId);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public List<User> listUserLimit(int start, int end) {
        return userDao.listUserLimit(start, end);
    }

    @Override
    public Long countAllUser() {
        return userDao.countAllUser();
    }

    @Override
    public Boolean delUser(User user) {
        return userDao.delete(user);
    }

}
