package cn.guet.navigator.web.service.impl;

import cn.guet.navigator.web.dao.PhotoDao;
import cn.guet.navigator.web.pojo.Photo;
import cn.guet.navigator.web.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Service
public class PhotoServiceImpl implements PhotoService{

    @Autowired
    private PhotoDao photoDao;

    @Override
    public Boolean savePhoto(Photo photo) {
        return photoDao.save(photo);
    }
}
