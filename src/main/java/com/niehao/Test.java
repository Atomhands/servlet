package com.niehao;

import com.niehao.bazi.Bazi;
import com.niehao.utils.SolarToLunar;
import com.niehao.wuxing.WuXing;
import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Arrays;
import java.util.Date;

import static com.niehao.utils.SolarToLunar.solarToLunar;
import static com.niehao.wuxing.WuXing.*;

/**
 * ClassName: Test
 * Package: com.niehao
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/10 19:04
 * @Version 1.0
 */
public class Test {
    @org.junit.Test
    public void ToDate(){
        System.out.println(solarToLunar(2002, 11, 25)[0]);
            System.out.println(Arrays.toString(solarToLunar(2002, 11, 25)));
    }
    @org.junit.Test
    public void calculate(){
        int[] date = new int[4];
        //solarToLunar(2002,11,25);
        for (int i = 0; i < 3; i++) {
            date[i] = solarToLunar(2023, 8, 12)[i];
        }
        Bazi.calculate(date[0],date[1],11,25,6);
    }
    @org.junit.Test
    public void TestTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        String time = sdf.format(date);
        System.out.println(time);
        int year = Integer.parseInt(time.substring(0,4));

        int month = Integer.parseInt(time.substring(5,7));

        int day = Integer.parseInt(time.substring(8,10));
        int hour = Integer.parseInt(time.substring(11,13));
        System.out.println(year+" "+month+" "+day+" "+hour);
    }
}
