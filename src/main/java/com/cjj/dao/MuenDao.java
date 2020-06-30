package com.cjj.dao;

import com.cjj.entity.Muen;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
public class MuenDao extends TemplateDao {

    public List<Muen> selectAll(){
        String sql="select * from muen";
        return template.query(sql,new BeanPropertyRowMapper<Muen>(Muen.class));
    }
}
