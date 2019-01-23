package cn.guet.navigator.web.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Administrator
 */
public class PositionVo implements Serializable {

    private static final long serialVersionUID = 6225714881759685127L;

    /**
     * 主键
     */
    private String positionId;

    /**
     * 设备Id
     */
    private String deviceId;

    /**
     * 速度
     */
    private Double speed;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 创建时间
     */
    private Timestamp presentTime;

    /**
     * 当前路况
     */
    private String roadState;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Timestamp getPresentTime() {
        return presentTime;
    }

    public void setPresentTime(Timestamp presentTime) {
        this.presentTime = presentTime;
    }

    public String getRoadState() {
        return roadState;
    }

    public void setRoadState(String roadState) {
        this.roadState = roadState;
    }

    public PositionVo() {
    }
}
