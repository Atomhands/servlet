package com.niehao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: DataGrid
 * Package: com.niehao.dto
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/5 14:21
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGrid {
    private List<?> data; // 数据页
    private long total; // 总条数
}

