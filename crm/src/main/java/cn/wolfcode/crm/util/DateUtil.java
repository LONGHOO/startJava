package cn.wolfcode.crm.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Author: 十一
 * @Date: 2019-05-13 19:24
 * @Descrption
 **/
public class DateUtil {

    private DateUtil() {
    }

    public static Date getEndDate(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return calendar.getTime();
        }
        return null;
    }
}
