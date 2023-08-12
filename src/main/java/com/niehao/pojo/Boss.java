package com.niehao.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * ClassName: Boss
 * Package: com.niehao.pojo
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/3 18:57
 * @Version 1.0
 */
public class Boss {
    private String bossId;
    private String account;
    private String password;
    private String name;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd,hh:mm:ss")
    private Date date;

    public Boss() {
    }

    public Boss(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public Boss(String account, String name, String phone, Date date) {
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.date = date;
    }

    public Boss(String bossId, String account, String password, String name, String phone, Date date) {
        this.bossId = bossId;
        this.account = account;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.date = date;
    }

    public String getBossId() {
        return bossId;
    }

    public void setBossId(String bossId) {
        this.bossId = bossId;
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

    @Override
    public String toString() {
        return "Boss{" +
                "bossId='" + bossId + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", date=" + date +
                '}';
    }
}
