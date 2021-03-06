package com.yeb.server.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *  日期转换
 * </p>
 *
 * @author Eddie
 * @since 2022-01-25
 */
@Component
public class DateConverterUtils implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String s) {
        try{
            return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
