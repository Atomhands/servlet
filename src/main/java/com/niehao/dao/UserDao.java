package com.niehao.dao;

import com.niehao.pojo.Boss;
import com.niehao.pojo.User;
import com.niehao.utils.DataSourceUtil;
import com.niehao.utils.DateToTimestamp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Date;

/**
 * ClassName: UserDao
 * Package: com.niehao.dao
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/11 17:29
 * @Version 1.0
 */
public class UserDao {
    public User queryUserAccount(String account)throws Exception {
        User user = null;
            Connection connection = DataSourceUtil.get();
            PreparedStatement pst = connection.prepareStatement("SELECT *from TB_USERVIP where account =?");
            pst.setString(1,account);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            user = new User(
                rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getTimestamp(6),
                    rs.getString(7)
            );
        }
        return user;
    }

    public User findAccount(String id) throws Exception{
        User user = null;
        Connection connection = DataSourceUtil.get();
        PreparedStatement pst = connection.prepareStatement("select account,name,phone,join_date from TB_USERVIP where user_Id = ?");
        pst.setString(1,id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            user = new User(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getTimestamp(4)
            );
            System.out.println(user);
        }
        return user;
    }

    public void saveUser(User user)throws Exception {
        Connection connection = DataSourceUtil.get();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO TB_USERVIP VALUES(?,?,?,?,?,?,?)");
        pst.setString(1, user.getId());
        pst.setString(2, user.getAccount());
        pst.setString(3, user.getPassword());
        pst.setString(4, user.getAccount());
        pst.setString(5, user.getPhone());
        pst.setTimestamp(6, DateToTimestamp.parseTimeStamp(user.getDate()));
        pst.setString(7, user.getActive());
        pst.executeUpdate();
    }
}
