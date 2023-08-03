package com.niehao.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * ClassName: DateToTimestamp
 * Package: com.niehao.design.utils
 * Description:
 *
 * @Author NieHao
 * @Create 2023/7/25 13:41
 * @Version 1.0
 */
public class DateToTimestamp {
    public static Timestamp parseTimeStamp(Date date){
        return new Timestamp(date.getTime());
    }
}
