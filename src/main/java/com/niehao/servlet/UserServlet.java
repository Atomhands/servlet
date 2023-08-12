package com.niehao.servlet;

import cn.hutool.core.convert.Convert;
import com.niehao.controller.EmpController;
import com.niehao.controller.UserController;
import com.niehao.dto.HttpResult;

import com.niehao.dto.Time;
import com.niehao.pojo.Boss;
import com.niehao.pojo.User;
import com.niehao.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static javax.swing.text.html.CSS.getAttribute;

/**
 * ClassName: UserServlet
 * Package: com.niehao.servlet
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/11 17:02
 * @Version 1.0
 */
@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    private SimpleDateFormat sdf;
    private UserController controller;
    private HttpSession session;
    public void init(){
        controller = new UserController();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        session = null;
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受链接
        String url = req.getRequestURI().replace("/user/", "");
        //设置初始结果为空
        HttpResult result = null;
        try {
            DataSourceUtil.set();
            //执行选择目标业务
            if (url.equals("login")) result = login(req);
            if (url.equals("information")) result = querySelect(req);
     //       if (url.equals("calculate")) result = selectTime(req);
            if (url.equals("getTime")) result = SysgetTime(req);
            if (url.equals("getLoginTime")) result = UsergetTime(req);
            if (url.equals("saveTime")) result = UsersetTime(req);
            //null
            if (result==null) JSONUtil.writeJson(resp, result);
            // commit
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.commit();
            // 响应
            JSONUtil.writeJson(resp, result);
        } catch (Exception e) {
            //回滚
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.rollback();

            String msg = e.getLocalizedMessage();
            //取得异常数据
            result = new HttpResult(false, msg, null, -400);
            JSONUtil.writeJson(resp,result);
            e.printStackTrace();
        } finally {
            DataSourceUtil.remove();
        }
    }

    //重新选择时间 new set session
    private HttpResult UsersetTime(HttpServletRequest req) throws ParseException {
        String date = req.getParameter("birthTime");
        session = req.getSession();
        String birthDate="";
        if (date.contains("T")) {
            birthDate = date.replace("T", " ");
        }
        User user = new User();
        user.setDate(sdf.parse(birthDate));
        session.setAttribute("birthDate",birthDate);
        return UsergetTime(req);
    }
    //get session
    private HttpResult UsergetTime(HttpServletRequest req) throws ParseException {
        String birth =String.valueOf(session.getAttribute("birthDate"));

        int year = Integer.parseInt(birth.substring(0,4));

        int month = Integer.parseInt(birth.substring(5,7));

        int day = Integer.parseInt(birth.substring(8,10));
        int hour = Integer.parseInt(birth.substring(11,13));
        Time dateT = new Time(year,month,day,hour);
        return controller.DateTo(dateT);
    }

    private HttpResult SysgetTime(HttpServletRequest req) {
        Date date = new Date();
        String time = sdf.format(date);
        int year = Integer.parseInt(time.substring(0,4));

        int month = Integer.parseInt(time.substring(5,7));

        int day = Integer.parseInt(time.substring(8,10));
        int hour = Integer.parseInt(time.substring(11,13));
        Time dateT = new Time(year,month,day,hour);
        return controller.DateTo(dateT);
    }

    private HttpResult selectTime(HttpServletRequest req) {

        return null;
    }

    private HttpResult querySelect(HttpServletRequest req)throws Exception {
        HttpSession session = req.getSession();
        //通过主键查询 id
        String id = Convert.toStr(session.getAttribute("id"));
        return controller.querySelect(id);
    }

    private HttpResult login(HttpServletRequest req) throws Exception {


        //接受ajax传递的参数
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String birth = req.getParameter("birth");

        //session 保存会话
        session = req.getSession();

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setName(account);
        user.setId(UuidUtil.uuid());
        user.setActive("Y");
        String birthDate = "";
        if (birth.contains("T")) {
            birthDate = birth.replace("T", " ");
        }
        System.out.println(birth);
        System.out.println(birthDate);
        user.setDate(sdf.parse(birthDate));
        //保存登陆的日期
        session.setAttribute("birthDate",birthDate);
        return controller.login(user, session);
    }
}
