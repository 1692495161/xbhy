package com.cjj.dao;

import com.cjj.entity.Page;
import com.cjj.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author cjj
 * @date 2020/7/1
 * @description
 */
public class PoiDao extends TemplateDao {
    public List<User> export(String username, String sex, Page page) {
        String sql = "SELECT " +
                "u.id id, " +
                "u.dept_id dept_id, " +
                "u.username username, " +
                "u.email email, " +
                "u.age age, " +
                "u.sex sex, " +
                "u.description description, " +
                "d.NAME deptName, " +
                "u.register_time register_time  " +
                "FROM " +
                "user u " +
                "LEFT JOIN dept d ON u.dept_id = d.id " +
                "WHERE " +
                "username LIKE ? " +
                "LIMIT ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class),
                "%" + username + "%", (page.getPageCurrent() - 1) * page.getSize(), page.getSize());

    }
}
