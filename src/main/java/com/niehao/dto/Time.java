package com.niehao.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Time
 * Package: com.niehao.dto
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/12 11:25
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Time {
    private int year;
    private int month;
    private int Nmonth;
    private int day;
    private int hour;

    public Time(int year, int month, int day, int hour) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
    }
}
