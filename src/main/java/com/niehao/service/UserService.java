package com.niehao.service;

import com.niehao.dao.UserDao;
import com.niehao.pojo.User;

/**
 * ClassName: UserService
 * Package: com.niehao.service
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/11 17:29
 * @Version 1.0
 */
public class UserService {
    private UserDao dao;
    {
        dao = new UserDao();
    }
    public User queryUserAccount(String account) throws Exception{
        return dao.queryUserAccount(account);
    }

    public User findAccount(String id)throws Exception {
        return dao.findAccount(id);
    }

    public Object queryAccount(String account) throws Exception {
        return dao.queryUserAccount(account);
    }

    public void saveUser(User user)throws Exception {
        dao.saveUser(user);
    }
}
