package com.cjj.service;

import com.cjj.dao.DeptDao;
import com.cjj.entity.Dept;

import java.util.List;

/**
 * @author cjj
 * @date 2020/6/23
 * @description
 */
public class DeptService {
    private DeptDao deptDao=new DeptDao();
    public List<Dept> list(){
        return deptDao.list();
    }
}
