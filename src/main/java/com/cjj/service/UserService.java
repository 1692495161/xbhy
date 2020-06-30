package com.cjj.service;

import com.cjj.dao.UserDao;
import com.cjj.entity.Page;
import com.cjj.entity.User;
import com.cjj.utils.MdUtil;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author cjj
 * @date 2020/6/23
 * @description
 */
public class UserService {
    private UserDao userDao=new UserDao();

    public Page listAll(String name,String sex,String pageStr){
        Page page=new Page();
        //当前页
        if (!StringUtils.isEmpty(pageStr)){
            page.setPageCurrent(Integer.valueOf(pageStr));
        }
        //总记录数
        page.setCount(userDao.getCount(name,sex));
        //总数据
        List<User> list = userDao.listAll(name,sex, page);
        page.setData(list);
        return page;
    }

//    public List<User> list(String name,String sex,Integer page){
//        return userDao.list(name,sex,page);
//    }

    public Integer getCount(String username,String sex){
        return userDao.getCount(username,sex);
    }

    public User findName(String name){
        return userDao.findName(name);
    }

    public void add(User user){
        user.setId(null);
        user.setPassword(MdUtil.md5(user.getPassword()));
        user.setRegisterTime(new Date());
        userDao.add(user);
    }

    public User getUserById(Integer id){
        return userDao.getUserById(id);
    }

    public void update(User user){
        userDao.update(user);
    }

    public void delete(Integer id){
        userDao.deleteUser(id);
    }


    public User checkLogin(String username,String newPwd){
        User user=new User();
        user.setUsername(username);
        user.setPassword(MdUtil.md5(newPwd));
        return userDao.checkLogin(user);
    }

    public void updateUserByName(String pwd,String name){
        userDao.updateUserByName(pwd,name);
    }

    public void updatePic(Integer id, String pic) {
        userDao.updatePic(id,pic);
    }

}
