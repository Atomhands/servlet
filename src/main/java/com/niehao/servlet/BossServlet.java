package com.niehao.servlet;

import com.niehao.controller.BossController;
import com.niehao.dto.HttpResult;
import com.niehao.pojo.Boss;
import com.niehao.utils.DataSourceUtil;
import com.niehao.utils.JSONUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        try {
            DataSourceUtil.set();
            //执行选择目标业务
            if (url.equals("login")) result =login(req);



            //commit
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.commit();
            //响应
            JSONUtil.writeJson(resp,result);
        } catch (Exception e) {
            //回滚
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.rollback();

            String msg = e.getLocalizedMessage();
            //取得异常数据
            result = new HttpResult(false,msg,null,-400);
            e.printStackTrace();
        }finally {
            DataSourceUtil.remove();
        }
    }

    private HttpResult login(HttpServletRequest req) {
     /*
     *  username: Boss
        password: 123456
     *
     * */
        //接受ajax传递的参数
        String account = req.getParameter("username");
        String password = req.getParameter("password");
        //session 保存会话
        HttpSession session = req.getSession();
        Boss boss = new Boss(account,password);
        return controller.login(boss,session);
    }
}
