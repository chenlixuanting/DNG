package cn.guet.navigator.web.dao;

import cn.guet.navigator.web.pojo.Photo;

/**
 * @author Administrator
 */
public interface PhotoDao {

    /**
     * 保存新的照片记录
     *
     * @param photo
     * @return
     */
    Boolean save(Photo photo);

}
