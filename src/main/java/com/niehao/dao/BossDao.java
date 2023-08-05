package com.niehao.dao;

import com.niehao.pojo.Boss;
import com.niehao.utils.DataSourceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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
}
