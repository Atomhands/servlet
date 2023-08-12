package com.niehao.bazi;

import com.niehao.dto.HttpResult;
import com.niehao.dto.HuangLi;
import com.niehao.dto.Time;
import com.niehao.wuxing.WuXing;

import static com.niehao.wuxing.WuXing.*;


/**
 * ClassName: Bazi
 * Package: com.niehao.bazi
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/11 19:47
 * @Version 1.0
 */

public class Bazi {
    private int year;
    private int nMonth;
    private int yMonth;
    private int day;
    private int time;
    public Bazi() {
    }

    public Bazi(int year, int nMonth, int yMonth, int day, int time) {
        this.year = year;
        this.nMonth = nMonth;
        this.yMonth = yMonth;
        this.day = day;
        this.time = time;
    }
    public static HttpResult calculate(int year, int nMonth, int yMonth, int day, int time){

        // 获取干支年
        String gzYear = getYear(year);
        System.out.println(year + " 年为 \"" + gzYear + "\" 年");

        // 获取干支月
        String gzMonth = getMonth(gzYear, nMonth);
   //     System.out.println(year + " 年农历 " + nMonth + " 月为 \"" + gzMonth + "\" 月");

        // 获取干支日
        String gzDay = getDay(year, yMonth, day);
   //     System.out.println(year + " 年阳历 " + yMonth + " 月 " + day + " 日为 \"" + gzDay + "\" 日");

        // 获取干支时
        String gzTime = getTime(gzDay, time);
    //    System.out.println(year + " 年阳历 " + yMonth + " 月 " + day + " 日 " + time + " 时为 \"" + gzTime + "\" 时");

        // 生辰八字
        System.out.println("您的生辰八字为：" + gzYear + gzMonth + gzDay + gzTime);
        HuangLi data = new HuangLi(gzYear , gzMonth , gzDay , gzTime);
        // 生辰八字算五行
        WuXing wuXing = new WuXing();
        wuXing.wuxing(gzYear, gzMonth, gzDay, gzTime);
        return new HttpResult(true,"true",data,200);
    }
}
