package com.guet.navigator.web.pojo;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class Line implements Serializable{

    private static final long serialVersionUID = -2371527419927010165L;

    private String name;
    private Point start;
    private Point end;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public Line() {
    }
}
