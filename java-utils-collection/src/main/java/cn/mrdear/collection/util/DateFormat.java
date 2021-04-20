package cn.mrdear.collection.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间处理工具,如果是多时区系统,需要加上时区,非多时区系统可以直接去掉zone参数
 *
 * @author Jeff Pan
 * @since 2019-02-12
 */
public enum DateFormat {

    YMD_LA(DateTimeConstant.YMD, ZoneId.of("America/Los_Angeles")),

    YMD_GMT8(DateTimeConstant.YMD, ZoneId.of("GMT+8")),

    ;

    /**
     * 格式
     */
    public final DateTimeFormatter pattern;

    DateFormat(DateTimeFormatter pattern, ZoneId zone) {
        this.pattern = pattern.withZone(zone);
    }

    /**
     * 格式化时间
     *
     * @param date 时间
     * @return 格式化结果
     */
    public String format(Date date) {
        return this.pattern.format(date.toInstant());
    }


    public Date parse(String date) {
        ZonedDateTime time = ZonedDateTime.parse(date, this.pattern);
        return Date.from(time.toInstant());
    }

    /**
     * 日期加减
     *
     * @param date 日期
     * @param day  增加天数
     * @return 结果
     */
    public Date addDay(Date date, int day) {
        Instant instant = ZonedDateTime.ofInstant(date.toInstant(), this.pattern.getZone())
            .plusDays(day)
            .toInstant();
        return new Date(instant.toEpochMilli());
    }

    /**
     * 同上
     *
     * @param date 日期
     * @param day  增加天数
     * @return 结果
     */
    public Date addDay(String date, int day) {
        Instant instant = ZonedDateTime.parse(date, this.pattern)
            .plusDays(day)
            .toInstant();
        return new Date(instant.toEpochMilli());
    }

    /**
     * 得到指定时区当前时间
     * @return 指定时区当前时间
     */
    public String getNow() {
        Instant instant = ZonedDateTime.now(this.pattern.getZone())
            .toInstant();
        return this.pattern.format(instant);
    }

}
