package cn.guet.navigator.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class Step implements Serializable {

    private static final long serialVersionUID = -7426045400481023829L;

    /**
     * 行驶指示
     */
    private String instruction;

    /**
     * 方向
     */
    private String orientation;

    /**
     * 道路名称
     */
    private String road;

    /**
     * 路段距离
     */
    private Integer distance;

    /**
     * 此段收费
     */
    private Double tolls;

    /**
     * 收费路段距离
     */
    private Integer toll_distance;

    /**
     * 收费路段
     */
    private List<String> toll_road = new ArrayList<String>();

    /**
     * 预计行驶时间
     */
    private Integer duration;

    /**
     * 坐标点串
     */
    private String polyline;

    /**
     * 导航主要动作
     */
    private String action;

    /**
     * 导航辅助动作
     */
    private List<String> assistant_action = new ArrayList<String>();

    /**
     * 驾驶导航详细信息
     */
    List<Tmc> tmcs = new ArrayList<Tmc>();

    /**
     * 途径城市
     */
    List<City> cities = new ArrayList<City>();

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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

    public List<String> getToll_road() {
        return toll_road;
    }

    public void setToll_road(List<String> toll_road) {
        this.toll_road = toll_road;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public List<String> getAssistant_action() {
        return assistant_action;
    }

    public void setAssistant_action(List<String> assistant_action) {
        this.assistant_action = assistant_action;
    }

    public List<Tmc> getTmcs() {
        return tmcs;
    }

    public void setTmcs(List<Tmc> tmcs) {
        this.tmcs = tmcs;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public Step() {
    }
}
