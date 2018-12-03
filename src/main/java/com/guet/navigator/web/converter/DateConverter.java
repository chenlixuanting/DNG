package com.guet.navigator.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * spring mvc 日期转换器
 *
 */
public class DateConverter implements Converter<String,Date>{

    @Override
    public Date convert(String source) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //设置SimpleDateFormat不自动计算日期
        sdf.setLenient(false);

        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
