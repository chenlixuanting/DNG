package cn.guet.navigator.web.dao.impl;

import cn.guet.navigator.web.dao.AdministratorDao;
import cn.guet.navigator.web.pojo.Administrator;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 * @date 9/13/2018
 */
@Repository
public class AdministratorDaoImpl extends BaseDaoImpl<Administrator> implements AdministratorDao {

    @Override
    public Administrator findByUserAccount(String account) {

        Administrator administrator = null;
        String hql = "from cn.guet.navigator.web.pojo.Administrator as t where t.account=:account";

        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("account", account);
            administrator = (Administrator) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return administrator;
    }

}
