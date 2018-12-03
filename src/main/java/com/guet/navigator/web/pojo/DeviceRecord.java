package com.guet.navigator.web.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 设备登录状态
 *
 */
public class DeviceRecord implements Serializable{

    private static final long serialVersionUID = -8730116173418419595L;

    private String recordId;    //主键
    private String userId;  //用户id
    private String deviceId;    //设备id
    private Timestamp createTime;   //创建时间
    private Timestamp updateTime;   //更新时间

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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

    public DeviceRecord(String userId, String deviceId, Timestamp createTime, Timestamp updateTime) {
        this.userId = userId;
        this.deviceId = deviceId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public DeviceRecord() {
    }

}
