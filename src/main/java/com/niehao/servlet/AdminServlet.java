package com.niehao.servlet;

import cn.hutool.core.util.StrUtil;
import com.niehao.controller.EmpController;
import com.niehao.dto.HttpResult;
import com.niehao.pojo.Emp;
import com.niehao.utils.DataSourceUtil;
import com.niehao.utils.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: AdminServlet
 * Package: com.niehao.servlet
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/5 9:29
 * @Version 1.0
 */
@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private EmpController controller;
    public void init(){
        controller = new EmpController();
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取链接
        String url = req.getRequestURI().replace("/admin/","");
        //设置结果数据
        Object result =null;
        //连接数据库
        try {
            DataSourceUtil.set();
            //判断业务
            if (StrUtil.equals("saveEmp",url)) result = saveEmp(req,resp);
            // commit
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.commit();
            // 响应
            JSONUtil.writeJson(resp, result);
        } catch (Exception e) {
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.rollback();
            String msg = e.getLocalizedMessage();
            result = new HttpResult(false, msg, null, -400);
            JSONUtil.writeJson(resp, result);
            e.printStackTrace();
        } finally {
            DataSourceUtil.remove();
        }
    }

    private Object saveEmp(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
