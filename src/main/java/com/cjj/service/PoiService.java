package com.cjj.service;

import com.cjj.dao.PoiDao;
import com.cjj.dao.UserDao;
import com.cjj.entity.Page;
import com.cjj.entity.User;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author cjj
 * @date 2020/7/1
 * @description
 */
public class PoiService {
    PoiDao poiDao = new PoiDao();
    UserDao userDao=new UserDao();

    public HSSFWorkbook export(String username,String sex,String strPage) {
        Page page=new Page();
        //设置当前页
        if (!StringUtils.isEmpty(strPage)){
            page.setPageCurrent(Integer.valueOf(strPage));
        }
        //总记录数
        page.setCount(userDao.getCount(username,sex));
        List<User> list = poiDao.export(username,sex,page);

        //先创建HSSFWorkbook对象（文件）
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建HSSFSheet对象（工作簿，即是表）
        HSSFSheet sheet = wb.createSheet("员工信息表");
        //创建行头
        String[] user = {"员工名", "邮箱", "性别", "年龄", "描述"};
        HSSFRow rowHead = sheet.createRow(0);
        for (int i = 0; i < user.length; i++) {
            rowHead.createCell(i).setCellValue(user[i]);
        }
        /*rowHead.createCell(0).setCellValue("员工名");
        rowHead.createCell(1).setCellValue("邮箱");
        rowHead.createCell(2).setCellValue("性别");
        rowHead.createCell(3).setCellValue("年龄");
        rowHead.createCell(4).setCellValue("描述");*/

        for (int i = 1; i <= list.size(); i++) {
            User user1=list.get(i-1);
            if ("1".equals(user1.getSex())){
                user1.setSex("男");
            }
            else if ("0".equals(user1.getSex())){
                user1.setSex("女");
            }else {
                user1.setSex("其他");
            }
            //创建row（行）
            HSSFRow row = sheet.createRow(i);
            //创建列（单元格）
            row.createCell(0).setCellValue(user1.getUsername()==null?"":user1.getUsername());
            row.createCell(1).setCellValue(user1.getEmail()==null?"":user1.getEmail());
            row.createCell(2).setCellValue(user1.getSex()==null?"":user1.getSex());
            row.createCell(3).setCellValue(user1.getAge()==null?"":user1.getAge().toString());
            row.createCell(4).setCellValue(user1.getDescription()==null?"":user1.getDescription());
        }
        return wb;
    }
}
