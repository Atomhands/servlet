package com.niehao.controller;

import com.niehao.dto.DataGrid;
import com.niehao.dto.HttpResult;
import com.niehao.dto.Page;
import com.niehao.pojo.Emp;
import com.niehao.service.EmpService;
import com.niehao.utils.EncryptUtil;
import com.niehao.utils.UuidUtil;

import java.util.Date;

/**
 * ClassName: EmpConctroller
 * Package: com.niehao.controller
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/5 9:47
 * @Version 1.0
 */
public class EmpController {
    private EmpService service;
    {
        service = new EmpService();
    }

    public DataGrid listEmp(Page page, Emp emp) throws Exception{
        page.setCurrent(page.getCurrent() +1);
        return service.listEmp(page,emp);
    }

    public HttpResult saveEmp(Emp emp)throws Exception {
        //初始密码
        emp.setPassword(EncryptUtil.md5("123456"));
        emp.setAccount(emp.getAccount());
        //id 自动随机
        emp.setEmpId(UuidUtil.uuid());
        emp.setSal(emp.getSal());
        service.saveEmp(emp);
        //
        return HttpResult.Confirm("录入员工",null);
    }

    public Object selectPhone(String phone) throws Exception{
        return service.selectPhone(phone);
    }
}
