package com.cjj.service;

import com.cjj.dao.MeetDao;
import com.cjj.entity.Meet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author cjj
 * @date 2020/6/30
 * @description
 */
public class MeetService {
    MeetDao meetDao = new MeetDao();

    public List<Meet> meetList(String name, Integer status) {
        return meetDao.meetList(name, status);
    }

    public void addMeet(Meet meet) {
        meet.setStatus(0);
        String strStart = meet.getStartTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yy:MM:dd HH:mm:ss");

        Date date = new Date();
        String date2 = sdf.format(date);
        try {
            Date date3 = sdf.parse(date2);
            meet.setPublishDate(date3);
            meetDao.addMeet(meet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Meet getMeetById(Integer id){
        return meetDao.getMeetById(id);
    }
}
