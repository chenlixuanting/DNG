package cn.guet.navigator.web.utils;

import cn.guet.navigator.web.constant.common.CommonConstant;

/**
 * 通过性别选取默认头像
 *
 * @author Administrator
 */
public class GetDefaultHeadPicUtil {

    /**
     * @param sex
     * @return
     */
    public static String getDefaultHeadPicBySex(String sex) {

        if (sex.equals(CommonConstant.MAN)) {
            return CommonConstant.MAN_PROFILE_URL;
        } else {
            return CommonConstant.WOMAN_PROFILE_URL;
        }

    }

}
