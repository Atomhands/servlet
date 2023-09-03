package com.niehao.servlet;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.niehao.controller.ProductController;
import com.niehao.dto.HttpResult;
import com.niehao.dto.Page;
import com.niehao.pojo.Product;
import com.niehao.utils.DataSourceUtil;
import com.niehao.utils.JSONUtil;
import com.niehao.utils.UuidUtil;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: ProductServlet
 * Package: com.niehao.servlet
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/9 16:16
 * @Version 1.0
 */
@WebServlet("/product/*")
public class ProductServlet extends HttpServlet {
    private ProductController productController;
    public void init(){
        productController = new ProductController();
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取链接
        String url = req.getRequestURI().replace("/product/","");
        //存放结果数据
        Object result = null;
        //连接数据库
        try{
            DataSourceUtil.set();
            //判断
            if (StrUtil.equals("addProduct",url)) result = addProduct(req,resp);
            if (url.equals("list")) result = listProduct(req,resp);
            //null
            if (result==null) JSONUtil.writeJson(resp,result);
            //提交 commit
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.commit();
            //响应
            JSONUtil.writeJson(resp,result);
        }catch (Exception e){
            //回滚
            if (!DataSourceUtil.autoCommit()) DataSourceUtil.rollback();
            String message = e.getLocalizedMessage();
            //失败
            result = new HttpResult(false,message,result,-4000);
            JSONUtil.writeJson(resp,result);
            e.printStackTrace();
        }finally {
            DataSourceUtil.remove();
        }
    }

    private Object listProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        /*
        *
        * pageIndex: 0
pageSize: 10
sortField:
sortOrder:
* */
        int currentP = Convert.toInt(req.getParameter("pageIndex"));
        int size = Convert.toInt(req.getParameter("pageSize"));
        String sortField = Convert.toStr(req.getParameter("sortField"));
        String sortOrder = Convert.toStr(req.getParameter("sortOrder"));
        Page page = new Page(currentP, size, sortField, sortOrder);
        return productController.listProduct(page,req);
    }

    private Object addProduct(HttpServletRequest req, HttpServletResponse resp) {
        //先获取前端返回的数据
        /*
        name: 海飞丝
        price: 5000
        inventory: 600
        * */
        //productController.addProduct();
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String inventory = req.getParameter("inventory");
        Product product = new Product();
        product.setId(UuidUtil.uuid());
        product.setName(name);
        product.setPrice(price);
        product.setInventory(inventory);
        return productController.addProduct(product);
    }
}
