package com.niehao.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: User
 * Package: com.niehao.pojo
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/11 17:29
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String account;
    private String password;
    private String name;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    private String active;
    public User(String account,String password){
        this.account = account;
        this.password = password;
    }

    public User(String account, String name, String phone, Date date) {
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.date = date;
    }

    public User(String id, String account, String name, String phone, Date date, String active) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", date=" + date +
                ", active='" + active + '\'' +
                '}';
    }
}
