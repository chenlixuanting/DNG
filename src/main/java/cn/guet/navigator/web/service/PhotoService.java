package cn.guet.navigator.web.service;

import cn.guet.navigator.web.pojo.Photo;

/**
 * @author Administrator
 */
public interface PhotoService {

    /**
     * 保存新图片记录
     *
     * @param photo
     * @return
     */
    Boolean savePhoto(Photo photo);

}
