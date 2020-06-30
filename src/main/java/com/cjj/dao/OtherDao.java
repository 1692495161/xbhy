package com.cjj.dao;

import com.cjj.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * @author cjj
 * @date 2020/6/30
 * @description
 */
public class OtherDao extends TemplateDao {
    public User list(String openid){
        String sql = "select * from user where openid=?";
        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),openid);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
