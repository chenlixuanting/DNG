package cn.guet.navigator.web.utils;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class Tmc implements Serializable {
    private static final long serialVersionUID = 7785479486343362599L;

    /**
     * 此段路的长度
     */
    private Integer distance;

    /**
     * 此段路的交通情况
     */
    private String status;

    /**
     * 此段路的轨迹
     */
    private String polyline;

    public Tmc() {
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }
}
