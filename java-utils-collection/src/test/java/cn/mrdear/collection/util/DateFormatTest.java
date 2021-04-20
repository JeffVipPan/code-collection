package cn.mrdear.collection.util;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class DateFormatTest {

    @Test
    public void format() {
        Date date = new Date(1574125838000L);

        String format = DateFormat.YMD_LA.format(date);
        Assert.assertEquals("20191118", format);

        format = DateFormat.YMD_GMT8.format(date);
        Assert.assertEquals("20191119", format);
    }


    @Test
    public void testParse() {
        String date = "20191118";

        Date parse = DateFormat.YMD_LA.parse(date);
        Assert.assertEquals(1574064000000L, parse.getTime());

        parse = DateFormat.YMD_GMT8.parse(date);
        Assert.assertEquals(1574006400000L, parse.getTime());
    }

    @Test
    public void testDateAdd() {
        String date = "20191104";

        Date day = DateFormat.YMD_GMT8.addDay(date, -1);
        Assert.assertEquals(1572710400000L, day.getTime());

        day = DateFormat.YMD_LA.addDay(date, -1);

        Assert.assertEquals(1572764400000L, day.getTime());
    }


}