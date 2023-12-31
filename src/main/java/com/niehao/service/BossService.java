package com.niehao.service;

import com.niehao.dao.BossDao;
import com.niehao.dto.DataGrid;
import com.niehao.dto.HttpResult;
import com.niehao.dto.Page;
import com.niehao.pojo.Boss;
import com.niehao.pojo.Product;
import com.niehao.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    public Boss queryBossAccount(String account) {
        return bossDao.queryBossAccount(account);
    }

    public Boss findAccount(String bossId) throws Exception{
        return bossDao.findAccount(bossId);
    }

    public Object listProduct(Page page, HttpServletRequest req)throws Exception {
        long total = bossDao.selectAll();
        List<User> list = bossDao.selectList(page,req);
        DataGrid dataGrid = new DataGrid();
        dataGrid.setTotal(total);
        dataGrid.setData(list);
        return dataGrid;
    }
}
