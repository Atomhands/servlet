package com.niehao.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.niehao.dto.HttpResult;
import com.niehao.pojo.Boss;
import com.niehao.service.BossService;

import javax.servlet.http.HttpSession;

/**
 * ClassName: Boss
 * Package: com.niehao.controller
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/3 9:15
 * @Version 1.0
 */
public class BossController {
    private BossService service;
    {
        service = new BossService();
    }

    public HttpResult login(Boss boss, HttpSession session) {
        //连接数据库 查找用户是否存在
        Boss data = service.queryBossAccount(boss.getAccount());
        //验证账号 密码
        if (ObjectUtil.isEmpty(data)) throw new RuntimeException("账号不存在");
        if (!StrUtil.equals(data.getPassword(), boss.getPassword())) throw new RuntimeException("密码错误");
        //保存会话
        session.setAttribute("bossId",data.getBossId());
        session.setAttribute("account",data.getAccount());
        session.setAttribute("jobTime",data.getDate());
        return new HttpResult(true,"登陆成功",data,200);
    }
}
