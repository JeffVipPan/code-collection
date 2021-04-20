package cn.imjeffpan.collection.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * @author Jeff Pan
 * @since 2019/11/19
 */
public class DateTimeConstant {

    public static final DateTimeFormatter YMD =
        new DateTimeFormatterBuilder().append(DateTimeFormatter.ofPattern("yyyyMMdd"))
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
        .toFormatter();

}
