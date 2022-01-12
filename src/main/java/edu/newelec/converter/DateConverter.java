package edu.newelec.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat simpleDateFormat = null;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = simpleDateFormat.parse(s);
            return utilDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
