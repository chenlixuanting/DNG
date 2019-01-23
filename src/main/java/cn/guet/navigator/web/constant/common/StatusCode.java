package cn.guet.navigator.web.constant.common;

/**
 * 状态码常量集合
 *
 * @author Administrator
 */
public class StatusCode {

    /**
     * 用户未扫码
     */
    public static final Integer USER_HAVE_NOT_SCAN_CODE = 100;

    /**
     * 用户已扫码
     */
    public static final Integer USER_HAVED_SCAN_CODE = 110;

    /**
     * 用户确认设备登录
     */
    public static final Integer USER_CONFIRM_DEVICE_LOGIN = 120;

    /**
     * 二维码过期
     */
    public static final Integer QRCODE_TIMEOUT = 130;

    /**
     * 账号已存在
     */
    public static final Integer ACCOUNT_EXIST = 300;

    /**
     * 账号不存在
     */
    public static final Integer ACCOUNT_NOT_EXIST = 310;

    /**
     * 存在空项
     */
    public static final Integer EXIST_EMPTY_ITME = 404;

    /**
     * 密码错误
     */
    public static final Integer PASSWORD_ERROR = 405;

    /**
     * 服务器内部错误
     */
    public static final Integer SERVER_INNER_ERROR = 500;

    /**
     * 成功
     */
    public static final Integer SUCCESS = 200;

}
