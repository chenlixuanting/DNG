package cn.guet.navigator.web.constant.user;

/**
 * @author Administrator
 */
public class MobileConstant {

    public static final String MESSAGES = "messages";
    public static final String SESSION_ID = "sessionId";
    public static final String STATUS_CODE = "statusCode";

    public static final int USER_PIC_HEAD = 1;
    public static final int USER_ID_CARD_FRONT_PIC = 2;
    public static final int USER_IC_CARD_REVERSE_PIC = 3;
    public static final int USER_DRIVER_LICENSE_PIC = 4;
    public static final int USER_DRIVER_PERMIT_PIC = 5;

    public static final String ROAD_STATUS_UNKNOW = "未知";
    public static final String ROAD_STATUS_UNBLOCK = "畅通";
    public static final String ROAD_STATUS_AMBLE = "缓行";
    public static final String ROAD_STATUS_CONGESTION = "拥堵";
    public static final String ROAD_STATUS_STRICT_CONGESTION = "严重拥堵";

    public static final String ROAD_STATUS = "status";
    public static final String ROAD_DURATION = "duration";
    public static final String ROAD_DISTANCE = "distance";

    public static final Double ROAD_STATUS_UNKNOW_VALUE = 0.0;
    public static final Double ROAD_STATUS_UNBLOCK_VALUE = 1.0;
    public static final Double ROAD_STATUS_AMBLE_VALUE = 4.0;
    public static final Double ROAD_STATUS_CONGESTION_VALUE = 8.0;
    public static final Double ROAD_STATUS_STRICT_CONGESTION_VALUE = 10.0;

    public static final int REQUEST_OK = 1;
    public static final int REQUEST_FAIL = 0;

}
