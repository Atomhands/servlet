package com.niehao.service;

import com.niehao.dao.BossDao;

/**
 * ClassName: BossService
 * Package: com.niehao.service
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/3 16:24
 * @Version 1.0
 */
public class BossService {
    private BossDao bossDao;
    {
        bossDao = new BossDao();
    }
}
