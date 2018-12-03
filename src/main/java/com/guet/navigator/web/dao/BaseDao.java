package com.guet.navigator.web.dao;

/**
 * Created by Administrator on 9/13/2018.
 */
public interface BaseDao<T> {

    /**
     * 增
     *
     * @param entity
     * @return
     */
    public Boolean save(T entity);


    /**
     * 删
     *
     * @param entity
     * @return
     */
    public Boolean delete(T entity);


    /**
     * 查
     *
     * @param id
     * @return
     */
    public T findById(String id);

    /**
     * 改
     *
     * @param entity
     * @return
     */
    public Boolean update(T entity);

}
