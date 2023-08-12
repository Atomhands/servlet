package com.niehao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: Page
 * Package: com.niehao.dto
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/5 14:12
 * @Version 1.0
 */

//页面设置
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
public class Page {
    private int current;
    private int size;
    private String sortField;
    private String sortOrder;
}
