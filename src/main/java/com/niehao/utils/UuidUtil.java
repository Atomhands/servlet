package com.niehao.utils;

import cn.hutool.core.lang.UUID;

/**
 * ClassName: UuidUtil
 * Package: com.niehao.design.utils
 * Description:
 *
 * @Author NieHao
 * @Create 2023/7/25 11:23
 * @Version 1.0
 */
public class UuidUtil {
    public static String uuid(){
        return UUID.fastUUID().toString().replace("-","").toUpperCase().substring(1,10);
    }
}
