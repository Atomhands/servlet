package com.niehao.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName: DataSourceUtil
 * Package: com.niehao.utils
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/3 17:00
 * @Version 1.0
 */
public class DataSourceUtil {
    //创建本地线程池
  //  ThreadLocal <Connection> threadLocal = new ThreadLocal<>();

    private final static ThreadLocal <Connection> threadLocal = new ThreadLocal<>();
    //构建数据库连接池
    private static DruidDataSource dataSource;
    //连接数据库
    static{
        try {
            dataSource = new DruidDataSource();
            dataSource.setDriverClassName("oracle.jdbc.OracleDriver");  //加载的驱动类
            dataSource.setUrl("jdbc:oracle:thin:@192.168.172.3:1521:orcl");
            dataSource.setUsername("scott");
            dataSource.setPassword("123456");
            dataSource.init();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //set get 方法
    public static void set() throws Exception {
        DruidPooledConnection conn = dataSource.getConnection();
        threadLocal.set(conn);
    }

    public static Connection get() {
        return threadLocal.get();
    }

    public static void remove() {
        try {
            get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            threadLocal.remove();
        }
    }

    //设置自动保存
    public static void setAutoCommit(boolean commit) throws Exception {
        get().setAutoCommit(commit);
    }
    //提交
    public static void commit() throws Exception {
        get().commit();
    }
    //回滚
    public static void rollback() {
        try {
            get().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean autoCommit() {
        try {
            return get().getAutoCommit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
