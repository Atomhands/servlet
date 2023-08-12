package com.niehao.dto;

import lombok.Data;

/**
 * ClassName: HttpResult
 * Package: com.niehao.dto
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/3 16:55
 * @Version 1.0
 */
@Data
public class HttpResult {
    private boolean isRequest;  //是否接受到请求
    private String message;// 响应信息
    private Object data; //响应数据
    private int code; // 当前状态码

    public HttpResult() {
    }

    public HttpResult(boolean isRequest, String message, Object data, int code) {
        this.isRequest = isRequest;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public HttpResult(String message, Object data) {
        this.isRequest = true;
        this.message = message;
        this.data = data;
        this.code = 2000;
    }

    public static HttpResult Confirm(String message,Object data) {
        return new HttpResult(message,data);
    }
}
