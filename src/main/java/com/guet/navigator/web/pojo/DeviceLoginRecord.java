package com.guet.navigator.web.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 设备登录记录
 * @author Administrator
 */
public class DeviceLoginRecord implements Serializable{

    private static final long serialVersionUID = -8730116173418419595L;

    /**
     * 主键
     */
    private String recordId;

    /**
     * 用户id
     */
    private User user;

    /**
     * 设备id
     */
    private Device device;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    private Timestamp updateTime;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public DeviceLoginRecord(User user, Device device, Timestamp createTime, Timestamp updateTime) {
        this.user = user;
        this.device = device;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public DeviceLoginRecord() {
    }

}
