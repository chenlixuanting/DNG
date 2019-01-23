package cn.guet.navigator.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 从身份证号码中提取生日
 *
 * @author Administrator
 */
public class GetBirthdayFromID {

    public static Date pick(String number) {
        StringBuilder sb = new StringBuilder();
        sb.append(number.substring(6, 10)).append("-").append(number.substring(10, 12)).append("-").append(number.substring(12, 14));
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sf.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
