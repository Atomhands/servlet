package com.niehao.controller;

import com.niehao.dto.HttpResult;
import com.niehao.pojo.Product;
import com.niehao.service.ProductService;

/**
 * ClassName: ProductControl
 * Package: com.niehao.controller
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/9 16:20
 * @Version 1.0
 */
public class ProductController {
    private ProductService service;
    {
        service = new ProductService();
    }

    public Object addProduct(Product product) {
        product.setKind("洗护 ");
        service.addProduct(product);
        return HttpResult.Confirm("添加产品",null);
    }
}
