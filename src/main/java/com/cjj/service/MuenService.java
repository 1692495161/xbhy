package com.cjj.service;

import com.cjj.dao.MuenDao;
import com.cjj.entity.Muen;

import java.util.List;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
public class MuenService {
    private MuenDao muenDao=new MuenDao();
    public List<Muen> select(){
        return muenDao.selectAll();
    }
}
