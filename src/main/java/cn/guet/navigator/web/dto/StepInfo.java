package cn.guet.navigator.web.dto;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class StepInfo implements Serializable {

    private static final long serialVersionUID = 6645519160249361254L;

    /**
     * 路段名称
     */
    private String stepname;

    /**
     * 距离
     */
    private Integer distance;

    /**
     * f花费时间
     */
    private Integer duration;

    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname;
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

    public StepInfo() {
    }
}
