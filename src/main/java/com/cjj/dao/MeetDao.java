package com.cjj.dao;

import com.cjj.entity.Meet;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author cjj
 * @date 2020/6/30
 * @description
 */
public class MeetDao extends TemplateDao {
    public List<Meet> meetList(){
        String sql="SELECT " +
                "dept_name ," +
                "dept_id ," +
                "title ," +
                "content ," +
                "STATUS  " +
                "FROM " +
                "meeting";
        return template.query(sql,new BeanPropertyRowMapper<>(Meet.class));
    }
}
