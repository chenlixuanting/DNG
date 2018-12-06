package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.UserDao;
import com.guet.navigator.web.pojo.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

    @Override
    public User findByUserAccount(String account) {

        User user = null;
        String hql = "from com.guet.navigator.web.pojo.User as u where u.account=:account";
        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("account",account);
            user = (User) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        save(user);
    }

    @Override
    public User findByUserId(String userId) {
        return findById(userId);
    }

    @Override
    public boolean updateUser(User user) {
        return update(user);
    }
}
