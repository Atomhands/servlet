package com.niehao.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: JSONUtil
 * Package: com.niehao.utils
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/3 17:17
 * @Version 1.0
 */
public class JSONUtil {
    public static void writeJson(HttpServletResponse resp,Object data){
        PrintWriter writer = null;
        try {
            resp.setCharacterEncoding("UTF-8");
            writer = resp.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(writer,data);
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }
}
