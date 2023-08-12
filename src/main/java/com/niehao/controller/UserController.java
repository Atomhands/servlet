package com.niehao.controller;

import cn.hutool.core.math.Calculator;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.niehao.bazi.Bazi;
import com.niehao.dto.HttpResult;

import com.niehao.dto.HuangLi;
import com.niehao.dto.Time;
import com.niehao.pojo.Boss;
import com.niehao.pojo.User;
import com.niehao.service.UserService;
import com.niehao.wuxing.WuXing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.niehao.utils.SolarToLunar.solarToLunar;

/**
 * ClassName: UserController
 * Package: com.niehao.controller
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/11 17:29
 * @Version 1.0
 */
public class UserController {
    private UserService service;
    {
        service = new UserService();
    }
    public HttpResult login(User user, HttpSession session)throws Exception {
        //连接数据库 查找用户是否存在
        User data = service.queryUserAccount(user.getAccount());
        //验证账号 密码
        if (ObjectUtil.isEmpty(data)) throw new RuntimeException("账号不存在");
        if (!StrUtil.equals(data.getPassword(), user.getPassword())) throw new RuntimeException("密码错误");
        //保存会话
        session.setAttribute("id",data.getId());
        session.setAttribute("account",data.getAccount());
        session.setAttribute("data",data.getDate());
        return new HttpResult(true,"登陆成功",data,200);
    }

    public HttpResult querySelect(String id) throws Exception{
        //查找用户
        User data = service.findAccount(id);
        return new HttpResult(true,"管理员信息",data,200);
    }

    public HttpResult DateTo(Time dateT) {
        int[] date = new int[3];
        //转农历
        for (int i = 0; i < 3; i++) {
            date[i] = solarToLunar(dateT.getYear(), dateT.getMonth(), dateT.getDay())[i];
        }
        //计算八字
        return Bazi.calculate(date[0],date[1],dateT.getMonth(),dateT.getDay(),dateT.getHour());
    }
}
