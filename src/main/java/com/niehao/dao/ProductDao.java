package com.niehao.dao;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.niehao.dto.Page;
import com.niehao.pojo.Emp;
import com.niehao.pojo.Product;
import com.niehao.utils.DataSourceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * ClassName: ProductDao
 * Package: com.niehao.dao
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/9 16:20
 * @Version 1.0
 */
public class ProductDao {
    public void addProduct(Product product) {
    }

    public long selectAll() throws Exception{
        Connection connection = DataSourceUtil.get();
        PreparedStatement pst = connection.prepareStatement("SELECT COUNT(*) FROM TB_PRODUCT");
        int rs = pst.executeUpdate();
        return rs;
    }

    public List<Product> selectList(Page page, HttpServletRequest req) throws Exception{
        //显示列表
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM " +
                "(SELECT A.*,ROWNUM RN FROM " +
                "(SELECT PRODUCTID,NAME,PRICE,inventory,kind,action FROM TB_PRODUCT ";
        // 将非空的值取出来
        Map<String, Object> map = new HashMap<>();
        BeanUtil.copyProperties( map, CopyOptions.create().ignoreNullValue());
        if (MapUtil.isNotEmpty(map)) {
            sql += "WHERE ";
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, Object>> its = entrySet.iterator();
            while (its.hasNext()) {
                Map.Entry<String, Object> next = its.next();
                String colum =next.getKey();
                sql += colum + " = ? AND";
            }
            // 去掉最后一个 AND
            sql = sql.substring(0, sql.length() - 3);
        }
        if (StrUtil.isNotEmpty(page.getSortOrder()) && StrUtil.isNotEmpty(page.getSortField())) {
            sql += "ORDER BY " + page.getSortField() + " " + page.getSortOrder();
        }

        sql += ") A WHERE ROWNUM <= ?) B WHERE B.RN >?";


        //     System.out.println(sql);
        Connection conn = DataSourceUtil.get();
        PreparedStatement pst = conn.prepareStatement(sql);

        if (MapUtil.isNotEmpty(map)) {
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, Object>> its = entrySet.iterator();
            int index = 1;
            while (its.hasNext()) {
                Object value = its.next().getValue();
                pst.setObject(index++, value);
            }
        }

        int s = (page.getCurrent() - 1) * page.getSize();
        int e = page.getCurrent() * page.getSize();
        int x = map.size();
        pst.setInt(x + 1, e);
        pst.setInt(x + 2, s);

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Product product = new Product(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
            list.add(product);
            // System.out.println(rs.getTimestamp(5));
        }
        return list;
    }
}
