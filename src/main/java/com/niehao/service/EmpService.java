package com.niehao.service;

import com.niehao.dao.Empdao;
import com.niehao.dto.DataGrid;
import com.niehao.dto.Page;
import com.niehao.pojo.Emp;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName: EmpService
 * Package: com.niehao.service
 * Description:
 *
 * @Author NieHao
 * @Create 2023/8/5 9:47
 * @Version 1.0
 */
public class EmpService {
    private Empdao empdao;
    {
        empdao = new Empdao();
    }
    public DataGrid listEmp(Page page, Emp emp) throws Exception{
        long total = empdao.selectAccount(emp);
        List<Emp> list = empdao.selectList(page,emp);
        DataGrid dataGrid = new DataGrid();
        dataGrid.setTotal(total);
        dataGrid.setData(list);
        return dataGrid;
    }

    public void saveEmp(Emp emp)throws Exception {
        empdao.saveEmp(emp);
    }

    public Object selectPhone(String phone) throws Exception{
        return empdao.selectPhone(phone);
    }

    public Object selectempId(HttpServletRequest req, Emp emp)throws Exception {
        return empdao.selectempId(req,emp);
    }
}
