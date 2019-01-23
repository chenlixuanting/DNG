package cn.guet.navigator.web.dao.impl;

import cn.guet.navigator.web.pojo.User;
import cn.guet.navigator.web.dao.UserDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User getByUserAccount(String account) {
        String hql = "from cn.guet.navigator.web.pojo.User as u where u.account=:account";
        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("account", account);
            return (User) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        save(user);
    }

    @Override
    public User getByUserId(String userId) {
        return findById(userId);
    }

    @Override
    public boolean updateUser(User user) {
        return update(user);
    }

    @Override
    public List<User> listUserLimit(int start, int end) {
        String hql = "from cn.guet.navigator.web.pojo.User";
        try {
            Query query = getCurrentSession().createQuery(hql);
            query.setFirstResult(start);
            query.setMaxResults(end);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long countAllUser() {
        String hql = "select count(*) from cn.guet.navigator.web.pojo.User";
        try {
            Query query = getCurrentSession().createQuery(hql);
            return (Long) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
