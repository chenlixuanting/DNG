package com.guet.navigator.web.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 设备登录状态
 */
public class DeviceRecord implements Serializable{

    private static final long serialVersionUID = -8730116173418419595L;

    private String recordId;
    private User user;  //用户id
    private Device device;    //设备id
    private Timestamp createTime;   //创建时间
    private Timestamp updateTime;   //更新时间

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

    public DeviceRecord(User user, Device device, Timestamp createTime, Timestamp updateTime) {
        this.user = user;
        this.device = device;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public DeviceRecord() {
    }

}
