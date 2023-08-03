package com.niehao.servlet;

import com.niehao.controller.BossController;

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

    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
