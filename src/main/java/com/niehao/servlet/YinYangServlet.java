package com.niehao.servlet;

import com.niehao.dto.HttpResult;
import com.niehao.pojo.HeTu;
import com.niehao.utils.DataSourceUtil;
import com.niehao.utils.JSONUtil;
import com.niehao.yinyang.YinYang;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: YinYangServlet
 * Package: com.niehao.servlet
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/16 1:05
 * @Version 1.0
 */
@WebServlet("/yinyang/*")
public class YinYangServlet extends HttpServlet {
    private YinYang yinYang;
    {
        yinYang = new YinYang();
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受链接
        String url = req.getRequestURI().replace("/yinyang/", "");
        //设置初始结果为空
        HttpResult result = null;
        try
            {
                DataSourceUtil.set();
        if (url.equals("Num3")) result = Num3(req);
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
        result = new HttpResult(false, msg, "error", -400);
        JSONUtil.writeJson(resp,result);
        e.printStackTrace();
    } finally {
        DataSourceUtil.remove();
        }
    }

    private HttpResult Num3(HttpServletRequest req) throws Exception{
        /*
        *   num1: 5
            num2: 3
            num3: 4
        * */
        System.out.println("Num3");
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));
        int num3 = Integer.parseInt(req.getParameter("num3"));
        if (num1>8) num1=num1%8;
        num1=(num1==0)?8:num1;

        if (num2>8) num2=num2%8;
        num2=(num2==0)?8:num2;

        if (num3>6) num3=num3%6;
        num3=(num3==0)?6:num3;
        HeTu data = new HeTu(num1,num2);
        System.out.println("1-乾 2-兑 3-离 4-震 5-巽 6-坎 7-艮 8-坤");
        System.out.println("上卦是:"+num1+"\t"+"下挂是"+num2+"\t"+"变爻是"+num3);
        System.out.println(data);
        return new HttpResult("1-乾 2-兑 3-离 4-震 5-巽 6-坎 7-艮 8-坤"+"上卦是:"+num1+"\t"+"下挂是"+num2+"\t"+"变爻是"+num3,data);
    }
}
