package com.niehao.service;

import com.niehao.dao.ProductDao;
import com.niehao.pojo.Product;

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
}
