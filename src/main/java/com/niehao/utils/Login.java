package com.niehao.utils;

import com.niehao.dto.HttpResult;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: Login
 * Package: com.niehao.utils
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/11 17:13
 * @Version 1.0
 */
public class Login {
    public static void loginInSys(String url, HttpResult result, HttpServletResponse resp, HttpServletRequest req){
//        try {
//            DataSourceUtil.set();
//            //执行选择目标业务
//            if (url.equals("login")) result =login(req);
//            if (url.equals("information")) result = querySelect(req);
//
//
//            //commit
//            if (!DataSourceUtil.autoCommit()) DataSourceUtil.commit();
//            //响应
//            JSONUtil.writeJson(resp,result);
//        } catch (Exception e) {
//            //回滚
//            if (!DataSourceUtil.autoCommit()) DataSourceUtil.rollback();
//
//            String msg = e.getLocalizedMessage();
//            //取得异常数据
//            result = new HttpResult(false,msg,null,-400);
//            e.printStackTrace();
//        }finally {
//            DataSourceUtil.remove();
//        }
    }
}
