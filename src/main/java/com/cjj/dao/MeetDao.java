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
    /*
    *@date 2020/7/1
    *@param [name, status]
    *@return java.util.List<com.cjj.entity.Meet>
    *@description 根据会议标题和状态进行查询
    */
    public List<Meet> meetList(String name,Integer status) {
        if (status==-1){
            String sql = "SELECT " +
                    "m.id id, " +
                    "d.name name, " +
                    "m.dept_name dept_name , " +
                    "m.title title, " +
                    "m.content content, " +
                    "m.status status, " +
                    "m.publish_date publish_date, " +
                    "m.start_time start_time, " +
                    "m.end_time end_time, " +
                    "m.make_user make_user " +
                    "FROM " +
                    "meeting m " +
                    "LEFT JOIN dept d ON m.dept_id = d.id " +
                    "where " +
                    "title like ?";
            return template.query(sql, new BeanPropertyRowMapper<>(Meet.class), "%" + name + "%");
        }else {
            String sql = "SELECT " +
                    "m.id id, " +
                    "d.name name, " +
                    "m.dept_name dept_name , " +
                    "m.title title, " +
                    "m.content content, " +
                    "m.status status, " +
                    "m.publish_date publish_date, " +
                    "m.start_time start_time, " +
                    "m.end_time end_time, " +
                    "m.make_user make_user " +
                    "FROM " +
                    "meeting m " +
                    "LEFT JOIN dept d ON m.dept_id = d.id " +
                    "where " +
                    "title like ? and status = ? ";
            return template.query(sql, new BeanPropertyRowMapper<>(Meet.class), "%" + name + "%",status);
        }
    }

    /*
    *@date 2020/7/1
    *@param [meet]
    *@return void
    *@description 新增会议
    */
    public void addMeet(Meet meet){
        String sql="insert into meeting(dept_name,title,content,publish_date,status,start_time) values (?,?,?,?,?,?)";
        template.update(sql,meet.getDeptName(),meet.getTitle(),meet.getContent(),meet.getPublishDate(),meet.getStatus(),meet.getStartTime());
    }

    public Meet getMeetById(Integer id){
        String sql="select * from meeting where id = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Meet.class),id);
    }
}
