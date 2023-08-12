package com.niehao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Product
 * Package: com.niehao.pojo
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/9 16:20
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private String price;
    private String inventory;
    private String photo;
    private String kind;

    public Product(String name, String price, String inventory) {
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }
}
