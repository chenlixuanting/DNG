package cn.guet.navigator.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 路线
 *
 * @author Administrator
 */
public class Route implements Serializable {

    private static final long serialVersionUID = 7355753361525020593L;

    /**
     * 起点坐标
     */
    private String origin;

    /**
     * 终点坐标
     */
    private String destination;

    /**
     * 打车费用，单位：元，注意：extensions=all时才会返回
     */
    private Double taxi_cost;

    /**
     * 车辆换乘方案
     */
    List<TransferScheme> paths = new ArrayList<TransferScheme>();

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getTaxi_cost() {
        return taxi_cost;
    }

    public void setTaxi_cost(Double taxi_cost) {
        this.taxi_cost = taxi_cost;
    }

    public List<TransferScheme> getPaths() {
        return paths;
    }

    public void setPaths(List<TransferScheme> paths) {
        this.paths = paths;
    }

    public Route() {
    }
}
