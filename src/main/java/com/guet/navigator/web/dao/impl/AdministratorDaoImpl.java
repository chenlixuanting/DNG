package com.guet.navigator.web.dao.impl;

import com.guet.navigator.web.dao.AdministratorDao;
import com.guet.navigator.web.pojo.Administrator;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 9/13/2018.
 */
@Repository
public class AdministratorDaoImpl extends BaseDaoImpl<Administrator> implements AdministratorDao {

    @Override
    public Administrator findByUserAccount(String account) {

        String hql = "from com.guet.navigator.web.pojo.Administrator as t where t.account=:account";

        Administrator administrator = null;

        try {
            Query query = getCurrentSession().createQuery(hql).setParameter("account", account);

            List<Administrator> administratorList = query.list();

            if (administratorList.size() > 0)
                administrator = administratorList.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return administrator;
    }

}
