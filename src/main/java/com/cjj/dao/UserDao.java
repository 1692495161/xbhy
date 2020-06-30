package com.cjj.dao;

import com.cjj.entity.Page;
import com.cjj.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author cjj
 * @date 2020/6/23
 * @description
 */
public class UserDao extends TemplateDao {
    public List<User> listAll(String name, String sex, Page page) {
        if ("-1".equals(sex)) {
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
                    "LEFT JOIN dept d ON u.dept_id = d.id  " +
                    "WHERE " +
                    "username LIKE ?  " +
                    "LIMIT ?,?";
            return template.query(sql, new BeanPropertyRowMapper<User>(User.class), "%" + name + "%", (page.getPageCurrent() - 1) * page.getSize(), page.getSize());
        } else {
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
                    "LEFT JOIN dept d ON u.dept_id = d.id  " +
                    "WHERE " +
                    "username LIKE ? and sex=?  " +
                    "LIMIT ?,?";
            return template.query(sql, new BeanPropertyRowMapper<User>(User.class), "%" + name + "%", sex, (page.getPageCurrent() - 1) * page.getSize(), page.getSize());
        }
    }

    //获取总条数
    public Integer getCount(String name, String sex) {

        if ("-1".equals(sex)) {
            String sql = "select count(*) from user where username like ? ";
            return template.queryForObject(sql, Integer.class, "%" + name + "%");
        } else {
            String sql = "select count(*) from user where username like ? and sex=?";
            return template.queryForObject(sql, Integer.class, "%" + name + "%", sex);
        }
    }

    /*
     *@date 2020/6/24
     *@param [name]
     *@return com.cjj.entity.User
     *@description  通过username判断数据库是否存在该用户
     */
    public User findName(String name) {
        String sql = "SELECT * from user where username=?";
        try {
            return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), name);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void add(User user) {
        String sql = "insert into user(username,password,email,age,sex,description,dept_name,register_time,real_name,wx_openid,pic) values(?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getAge(), user.getSex(), user.getDescription(), user.getDeptName(), user.getRegisterTime(),user.getRealName(),user.getWxOpenid(),user.getPic());
    }

    /*
    *@date 2020/6/28
    *@param [id]
    *@return com.cjj.entity.User
    *@description 通过id获取用户，回显数据
    */
    public User getUserById(Integer id) {
        String sql = "select * from user where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public void update(User user) {
        String sql = "update user set username=?,email=?,age=?,sex=?,description=? where id=?";
        template.update(sql, user.getUsername(), user.getEmail(), user.getAge(), user.getSex(), user.getDescription(), user.getId());
    }

    public void deleteUser(Integer id) {
        String sql = "delete from user where id =?";
        template.update(sql, id);
    }

    /*
    *@date 2020/6/28
    *@param [user]
    *@return com.cjj.entity.User
    *@description  登录界面查询是否存在该用户
    */
    public User checkLogin(User user) {
        String sql = "select * from user where username=? and password=?";
        try {
            return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),user.getUsername(),user.getPassword());
        } catch (DataAccessException e) {
            return null;
        }
    }

    /*
    *@date 2020/6/28
    *@param [pwd, name]
    *@return void
    *@description 忘记密码界面的修改
    */
    public void updateUserByName(String pwd,String name){
        String sql="update user set password=? where username=?";
        template.update(sql,pwd,name);
    }

    public void updatePic(Integer id, String pic) {
        String sql = "update user set pic=? where id=? ";
        template.update(sql, pic, id);
    }
}
