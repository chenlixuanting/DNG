package cn.guet.navigator.web.utils;

import cn.guet.navigator.web.constant.common.CommonConstant;

/**
 * 从身份证号码中提取性别
 *
 * @author Administrator
 */
public class GetSexFromID {
    public static String pick(String number) {
        return Integer.valueOf(number.charAt(16)) % 2 == 0 ? CommonConstant.WOMAN : CommonConstant.MAN;
    }
}
