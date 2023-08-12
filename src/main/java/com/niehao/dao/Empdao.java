package com.niehao.dao;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.niehao.dto.Page;
import com.niehao.pojo.Emp;
import com.niehao.utils.ConvertToCase;
import com.niehao.utils.DataSourceUtil;
import com.niehao.utils.DateToTimestamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * ClassName: Empdao
 * Package: com.niehao.dao
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/5 9:47
 * @Version 1.0
 */
public class Empdao {
    public long selectAccount(Emp emp)throws Exception {
        String sql = "Select Count(*) from Admin_Emp ";
        //取出非空值  存入map
        Map<String,Object> map = new HashMap<>();
        BeanUtil.copyProperties(emp,map, CopyOptions.create());
        // 判断map是否为空
        if (MapUtil.isNotEmpty(map)){
            // 追加sql语句
            sql += "where ";
            // 使用迭代器 打印 map
            Set<Map.Entry<String,Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String,Object>> iterator = entrySet.iterator();
            //while 循环
            while (iterator.hasNext()){
                Map.Entry<String,Object> next = iterator.next();
                //获取数据库的列名  下划线转驼峰命名
                String colum = next.getKey();
               // System.out.println(colum);
                //追加完整的sql语句
                sql +=" "+ colum + " = ?  ";
                sql +="AND";
            }
            // 去掉and
            sql = sql.substring(0,sql.length() -3);
        }
        //System.out.println(sql);
        //连接数据库
        Connection connection = DataSourceUtil.get();
        PreparedStatement pst =connection.prepareStatement(sql);
        // 进入数据库后  再次判断
        if (MapUtil.isNotEmpty(map)){
            // 使用迭代器 打印 map
            Set<Map.Entry<String,Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String,Object>> iterator = entrySet.iterator();
            //索引
            int index = 1;
            while (iterator.hasNext()){
                Object value = iterator.next().getValue();
                    pst.setObject(index++, value);
            }
        }
        //确认连接后打印
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            System.out.println(rs.getInt(1));
            return rs.getInt(1);
        }
        return 0;
    }

    public List<Emp> selectList(Page page, Emp emp)throws Exception {
        //显示员工列表
        List<Emp> list = new ArrayList<>();
        String sql = "SELECT * FROM " +
                "(SELECT A.*,ROWNUM RN FROM " +
                "(SELECT EmpId,ACCOUNT,NAME,PHONE,hireDate,sal FROM Admin_Emp ";
        // 将非空的值取出来
        Map<String, Object> map = new HashMap<>();
        BeanUtil.copyProperties(emp, map, CopyOptions.create().ignoreNullValue());
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
            Emp emp1 = new Emp(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getTimestamp(5),
                    rs.getString(6)
            );
            list.add(emp1);
           // System.out.println(rs.getTimestamp(5));
        }
        return list;
    }

    public void saveEmp(Emp emp) throws Exception{
        Connection connection = DataSourceUtil.get();
        PreparedStatement pst =connection.prepareStatement("INSERT INTO Admin_Emp values (?,?,?,?,?,?,?)");
        pst.setString(1,emp.getEmpId());
        pst.setString(2,emp.getAccount());
        pst.setString(3,emp.getPassword());
        pst.setString(4,emp.getName());
        pst.setString(5,emp.getPhone());
        pst.setTimestamp(6, DateToTimestamp.parseTimeStamp(emp.getHireDate()));
        pst.setString(7,emp.getSal());
        pst.executeUpdate();
    }

    public Object selectPhone(String phone) throws Exception{
        Emp emp = null;
        Connection connection = DataSourceUtil.get();
        PreparedStatement pst =connection.prepareStatement("Select*from admin_emp where phone =?");
        pst.setString(1,phone);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            emp = new Emp(rs.getString(1),
            rs.getString(2),
            rs.getString(3)
            );}
        return emp;
    }
}
