package com.niehao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: HuangLi
 * Package: com.niehao.dto
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/12 13:27
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class HuangLi {
    private String Tyear;
    private String Tmonth;
    private String Tday;
    private String Thour;

    public HuangLi(String tyear, String tmonth, String tday, String thour) {
        Tyear = tyear;
        Tmonth = tmonth;
        Tday = tday;
        Thour = thour;
    }
}
