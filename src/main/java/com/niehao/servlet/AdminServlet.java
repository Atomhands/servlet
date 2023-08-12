package com.niehao.servlet;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.niehao.controller.EmpController;
import com.niehao.dto.HttpResult;

import com.niehao.dto.Page;
import com.niehao.pojo.Emp;
import com.niehao.utils.DataSourceUtil;
import com.niehao.utils.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

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
    private SimpleDateFormat sdf;
    private EmpController controller;
    public void init(){
        controller = new EmpController();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
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
            if (StrUtil.equals("list",url)) result = listEmp(req,resp);
            if (StrUtil.equals("checkPhone",url)) result = checkPhone(req,resp);
            if (result==null){
                JSONUtil.writeJson(resp, result);
            }
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


    private Object checkPhone(HttpServletRequest req, HttpServletResponse resp) throws Exception{
    /*
    * phone:
    * */

        String phone = Convert.toStr(req.getParameter("phone"));
        Emp emp = new Emp();
        emp.setPhone(emp.getPhone());
        Object validate = controller.selectPhone(phone);
        if (ObjectUtil.isNotEmpty(validate)) {
            phone = null;
            JSONUtil.writeJson(resp,phone);
        }
        return phone;
    }


    private Object listEmp(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        /*
        *
        pageIndex: 0
        pageSize: 5
        sortField:
        sortOrder:
        * */
        int current = Convert.toInt(req.getParameter("pageIndex"));
        int size = Convert.toInt(req.getParameter("pageSize"));
        String sortField = Convert.toStr(req.getParameter("sortField"));
        String sortOrder = Convert.toStr(req.getParameter("sortOrder"));
        Page page = new Page(current, size, sortField, sortOrder);
        String phone = Convert.toStr(req.getParameter("phone"));
        //验证手机号
        Emp emp = new Emp();
        emp.setPhone(phone);
        System.out.println(phone);
        Object validate = controller.selectPhone(phone);
        if (ObjectUtil.isNotEmpty(validate))
            return selectPhone(page,emp);
        else {
            return controller.listEmp(page, emp);
        }
    }
    public Object selectPhone(Page page,Emp emp) throws Exception {
        return controller.listEmp(page, emp);
    }

    private Object saveEmp(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        /*
        name: haoge
phone: 8520
sal: 8520
hireDate: 2023-07-31T23:26
        * */
        String name = req.getParameter("name");
       // String gender = req.getParameter("gender");
        String phone = req.getParameter("phone");
        String sal = req.getParameter("sal");
        String hire = req.getParameter("hireDate");
        Emp emp = new Emp();
        emp.setPhone(phone);
        emp.setName(name);
        emp.setAccount(name);
        emp.setSal(sal);
       // System.out.println(hire);
      //  System.out.println(sdf.parse(hire));
        emp.setHireDate(sdf.parse(hire));

        return controller.saveEmp(emp);
    }
}
