package com.niehao.servlet;

import cn.hutool.core.convert.Convert;
import com.niehao.controller.BossController;
import com.niehao.dto.HttpResult;
import com.niehao.dto.Page;
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
            if (url.equals("information")) result = querySelect(req);
            if (url.equals("listUser")) result = (HttpResult) listUser(req,resp);
            //null
            if (result==null) JSONUtil.writeJson(resp, result);
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

    private Object listUser(HttpServletRequest req, HttpServletResponse resp)throws Exception {
//        pageIndex: 0
//        pageSize: 10
//        sortField:
//        sortOrder:
        int currentU = Convert.toInt(req.getParameter("pageIndex"));
        int size = Convert.toInt(req.getParameter("pageSize"));
        String sortField = Convert.toStr(req.getParameter("sortField"));
        String sortOrder = Convert.toStr(req.getParameter("sortOrder"));
        Page page = new Page(currentU, size, sortField, sortOrder);
        return controller.listProduct(page,req);
    }

    private HttpResult querySelect(HttpServletRequest req)throws Exception {
        HttpSession session = req.getSession();
        //通过主键查询 id
        String bossId = Convert.toStr(session.getAttribute("bossId"));
        return controller.querySelect(bossId);
    }

    private HttpResult login(HttpServletRequest req) {
     /*
     *  account: Boss
        password: 123456
     *
     * */
        //接受ajax传递的参数
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        //session 保存会话
        HttpSession session = req.getSession();
        Boss boss = new Boss(account,password);
        return controller.login(boss,session);
    }
}
