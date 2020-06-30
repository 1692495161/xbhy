package com.cjj.service;

import com.cjj.dao.OtherDao;
import com.cjj.entity.User;

/**
 * @author cjj
 * @date 2020/6/30
 * @description
 */
public class OtherService {
    OtherDao otherDao=new OtherDao();
    public User list(String openid){
        return otherDao.list(openid);
    }
}
