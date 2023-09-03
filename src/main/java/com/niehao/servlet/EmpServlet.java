package com.niehao.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: EmpServlet
 * Package: com.niehao.servlet
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/16 0:08
 * @Version 1.0
 */
@WebServlet("/emp/*")
public class EmpServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
