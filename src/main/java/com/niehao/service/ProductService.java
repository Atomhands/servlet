package com.niehao.service;

import com.niehao.dao.ProductDao;
import com.niehao.dto.DataGrid;
import com.niehao.dto.Page;
import com.niehao.pojo.Emp;
import com.niehao.pojo.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName: ProductService
 * Package: com.niehao.service
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/9 16:21
 * @Version 1.0
 */
public class ProductService {
        private ProductDao dao;
        {
                dao = new ProductDao();
        }

        public void addProduct(Product product) {
                dao.addProduct(product);
        }

    public Object listProduct(Page page, HttpServletRequest req)throws Exception {
        long total = dao.selectAll();
        List<Product> list = dao.selectList(page,req);
        DataGrid dataGrid = new DataGrid();
        dataGrid.setTotal(total);
        dataGrid.setData(list);
        return dataGrid;
    }
}
