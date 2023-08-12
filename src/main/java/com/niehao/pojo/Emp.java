package com.niehao.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * ClassName: Emp
 * Package: com.niehao.design.pojo
 * Description:
 *
 * @Author NieHao
 * @Create 2023/7/24 16:15
 * @Version 1.0
 */
public class Emp {
    private String empId;
    private String account;
    private String password;
    private String name;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date hireDate;
    private String sal;
    public Emp(){

    }

    public Emp(String account) {
        this.account = account;
    }

    public Emp(String account, String password, String phone) {
        this.account = account;
        this.password = password;
        this.phone = phone;
    }

    public Emp(String empId, String account, String name, String phone, Date hireDate, String sal) {
        this.empId = empId;
        this.account = account;
        this.name = name;
        this.phone = phone;
        this.hireDate = hireDate;
        this.sal = sal;
    }

    public Emp(String empId, String account, String password, String name, String phone, Date hireDate, String sal) {
        this.empId = empId;
        this.account = account;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.hireDate = hireDate;
        this.sal = sal;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }


    @Override
    public String toString() {
        return "Emp{" +
                "EmpId='" + empId + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", HireDate=" + hireDate +
                ", sal=" + sal +
                '}';
    }
}
