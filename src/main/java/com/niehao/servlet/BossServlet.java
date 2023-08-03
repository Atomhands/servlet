package com.niehao.servlet;

import com.niehao.controller.BossController;
import com.niehao.dto.HttpResult;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: BossServlet
 * Package: com.niehao.servlet
 * Description:
 *
 * @Author niehao
 * @Create 2023/8/3 15:54
 * @Version 1.0
 */
@WebServlet("/boss/*")
public class BossServlet extends HttpServlet {
    private BossController controller;
    public void init(ServletConfig config)throws ServletException{
        controller = new BossController();
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受链接
        String url = req.getRequestURI().replace("/boss/","");
        //设置初始结果为空
        HttpResult result = null;
        // 从数据库拿取数据

        //执行选择目标业务
    }
}
