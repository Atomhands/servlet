package com.niehao.utils;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * ClassName: EncryptUtil
 * Package: com.niehao.design.utils
 * Description:
 *
 * @Author NieHao
 * @Create 2023/7/24 18:18
 * @Version 1.0
 */
public class EncryptUtil {
    public static String md5(String encryptPassword){
        return DigestUtil.md5Hex(encryptPassword).substring(1,15);
    }
}
