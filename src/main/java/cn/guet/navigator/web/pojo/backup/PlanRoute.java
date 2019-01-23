package cn.guet.navigator.web.pojo.backup;

import java.io.Serializable;
import java.util.List;

public class PlanRoute implements Serializable{

    private List<Point> from;
    private List<Point> points;
    private List<Point> to;

    public List<Point> getFrom() {
        return from;
    }

    public void setFrom(List<Point> from) {
        this.from = from;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public List<Point> getTo() {
        return to;
    }

    public void setTo(List<Point> to) {
        this.to = to;
    }

    public PlanRoute() {
    }
}
