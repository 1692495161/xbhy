package com.cjj.dao;

import com.cjj.entity.Dept;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author cjj
 * @date 2020/6/23
 * @description
 */
public class DeptDao extends TemplateDao {
    public List<Dept> list(){
        String sql="select id,name from dept";
        return template.query(sql,new BeanPropertyRowMapper<Dept>(Dept.class));
    }
}
