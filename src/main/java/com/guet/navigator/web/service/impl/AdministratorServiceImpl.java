package com.guet.navigator.web.service.impl;

import com.guet.navigator.web.constant.SessionKey;
import com.guet.navigator.web.dao.AdministratorDao;
import com.guet.navigator.web.pojo.Administrator;
import com.guet.navigator.web.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 9/13/2018.
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorDao administratorDao;

    @Override
    public Administrator findByUserAccount(String account) {
        return administratorDao.findByUserAccount(account);
    }

    /**
     * 通过token判断用户是否重复提交登陆表单
     *
     * @param session
     * @param postToken
     * @return
     */
    public boolean isRepeatSubmit(HttpSession session, String postToken) {

        String sessionToken = (String) session.getAttribute(SessionKey.TOKEN);

        //如果表单中的token为空则为重复提交
        if (StringUtils.isEmpty(postToken) || StringUtils.isEmpty(sessionToken) || (!sessionToken.equals(postToken))) {
            return true;
        } else {
            return false;
        }

    }

}
