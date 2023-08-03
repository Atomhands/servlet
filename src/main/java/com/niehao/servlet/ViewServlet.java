package com.niehao.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: ViewServlet
 * Package: com.niehao.servlet
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/3 9:15
 * @Version 1.0
 */
public class ViewServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String viewName = "/WEB-INF/view"+url;
        req.getRequestDispatcher(viewName).forward(req,resp);
    }
}
