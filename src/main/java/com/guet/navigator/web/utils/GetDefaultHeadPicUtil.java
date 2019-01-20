package com.guet.navigator.web.utils;

import com.guet.navigator.web.constant.common.CommonConstant;

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

        if (sex.equals("男")) {
            return CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_PROFILE_PIC_DEFAULT + CommonConstant.MAN_DEFAULT_PROFILE_PIC;
        } else {
            return CommonConstant.LOCAL_NET_WEB + CommonConstant.USER_PROFILE_PIC_DEFAULT + CommonConstant.WOMAN_DEFAULT_PROFILE_PIC;
        }

    }

}
