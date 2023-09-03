package com.niehao.dao;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.niehao.dto.Page;
import com.niehao.pojo.Boss;
import com.niehao.pojo.Product;
import com.niehao.pojo.User;
import com.niehao.utils.DataSourceUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ClassName: BossDao
 * Package: com.niehao.dao
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/3 16:24
 * @Version 1.0
 */
public class BossDao {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public Boss queryBossAccount(String account) {
        Boss boss = null;
        //连接数据库
        try {
            Connection connection = DataSourceUtil.get();
            PreparedStatement pst = connection.prepareStatement("Select*from tb_Boss where account=?");
            pst.setString(1,account);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                boss = new Boss(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6)
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return boss;
    }

    public Boss findAccount(String bossId)throws Exception {
        Boss boss = null;
        Connection connection = DataSourceUtil.get();
        PreparedStatement pst = connection.prepareStatement("select account,name,phone,job_Time from tb_Boss where boss_Id = ?");
        pst.setString(1,bossId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            boss = new Boss(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getTimestamp(4)
            );
            System.out.println(boss);
        }
        return boss;
    }

    public long selectAll() throws Exception{
        Connection connection = DataSourceUtil.get();
        PreparedStatement pst = connection.prepareStatement("SELECT COUNT(*) FROM TB_PRODUCT");
        int rs = pst.executeUpdate();
        return rs;
    }

    public List<User> selectList(Page page, HttpServletRequest req)throws Exception {
        //显示列表
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM " +
                "(SELECT A.*,ROWNUM RN FROM " +
                "(SELECT USER_ID,ACCOUNT,NAME,PHONE,JOIN_DATE,ACTIVE FROM TB_USERVIP ";
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
            User user = new User(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getTimestamp(5),
                    rs.getString(6)
            );
            list.add(user);
            // System.out.println(rs.getTimestamp(5));
        }
        return list;
    }
}
