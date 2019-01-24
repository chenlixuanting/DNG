package cn.guet.navigator.web.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 途径路线
 *
 * @author Administrator
 */
public class Path implements Serializable {

    private Location start;
    private List<Location> viaPoint;
    private Location end;

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public List<Location> getViaPoint() {
        return viaPoint;
    }

    public void setViaPoint(List<Location> viaPoint) {
        this.viaPoint = viaPoint;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    public Path() {
    }
}
