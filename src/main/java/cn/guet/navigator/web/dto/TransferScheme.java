package cn.guet.navigator.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class TransferScheme implements Serializable {

    private static final long serialVersionUID = 5790873369977212996L;

    /**
     * 行驶距离 单位:米
     */
    private Integer distance;

    /**
     * 预计行驶时间 单位:秒
     */
    private Integer duration;

    /**
     * 导航策略
     */
    private String strategy;

    /**
     * 此导航方案道路收费 单位:元
     */
    private Double tolls;

    /**
     * 收费路段距离 单位:米
     */
    private Integer toll_distance;

    /**
     * 限行结果
     * 0 代表限行已规避或未限行，即该路线没有限行路段
     * 1 代表限行无法规避，即该线路有限行路段
     */
    private Integer restriction;

    /**
     * 红绿灯个数
     */
    private Integer traffic_lights;

    /**
     * 导航路段
     */
    List<Step> steps = new ArrayList<Step>();

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Double getTolls() {
        return tolls;
    }

    public void setTolls(Double tolls) {
        this.tolls = tolls;
    }

    public Integer getToll_distance() {
        return toll_distance;
    }

    public void setToll_distance(Integer toll_distance) {
        this.toll_distance = toll_distance;
    }

    public Integer getRestriction() {
        return restriction;
    }

    public void setRestriction(Integer restriction) {
        this.restriction = restriction;
    }

    public Integer getTraffic_lights() {
        return traffic_lights;
    }

    public void setTraffic_lights(Integer traffic_lights) {
        this.traffic_lights = traffic_lights;
    }

    public TransferScheme() {
    }
}
