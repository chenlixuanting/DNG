package cn.guet.navigator.web.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class Navigation implements Serializable {

    private static final long serialVersionUID = -668414505869342532L;

    /**
     * api请求结果 1:成功 0:失败
     */
    private Integer status;

    /**
     * 请求返回状态说明。status=1时返回OK否则返回错误信息
     */
    private String info;

    /**
     * 请求状态说明码 1000 请求正常
     */
    private Integer infocode;

    /**
     * 驾车路径规划方案数目
     */
    private Integer count;

    /**
     * 驾车路径规划信息列表
     */
    List<Route> route = new ArrayList<Route>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getInfocode() {
        return infocode;
    }

    public void setInfocode(Integer infocode) {
        this.infocode = infocode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Route> getRoute() {
        return route;
    }

    public void setRoute(List<Route> route) {
        this.route = route;
    }

    public Navigation() {
    }
}
