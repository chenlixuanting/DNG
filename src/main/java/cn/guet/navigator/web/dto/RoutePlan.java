package cn.guet.navigator.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class RoutePlan implements Serializable {

    private static final long serialVersionUID = 120529322060829852L;

    /**
     * 方案名称
     */
    private String planname;

    /**
     * 距离
     */
    private Integer distance;

    /**
     * 花费时间
     */
    private Integer duration;

    /**
     * 步骤
     */
    private List<StepInfo> stepInfos = new ArrayList<StepInfo>();

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
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

    public List<StepInfo> getStepInfos() {
        return stepInfos;
    }

    public void setStepInfos(List<StepInfo> stepInfos) {
        this.stepInfos = stepInfos;
    }

    public RoutePlan() {
    }
}
