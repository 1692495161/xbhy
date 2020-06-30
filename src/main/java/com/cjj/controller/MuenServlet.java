package com.cjj.controller;

import com.cjj.entity.Muen;
import com.cjj.service.MuenService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
@WebServlet("/muen")
public class MuenServlet extends HttpServlet {
    private MuenService muenService = new MuenService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper om = new ObjectMapper();
        //接受查询到的表信息
        List<Muen> list = muenService.select();
        //创建两个list集合
        List<Muen> parent = new ArrayList<>();
        List<Muen> son = new ArrayList<>();

        //遍历list集合
        for (Muen m : list) {
            if (m.getType()==1) {
                //如果是一级目录则加入parent集合
                parent.add(m);
            }
            if (m.getType()==2) {
                //如果是二级目录则加入son集合
                son.add(m);
            }
        }

        //将集合加入Map集合，并传入前端
        Map<String, List<Muen>> map = new HashMap<>();
        map.put("parent", parent);
        map.put("son", son);

        //将map转换成json格式
        String strMap = om.writeValueAsString(map);

        PrintWriter pw = response.getWriter();
        pw.write(strMap);
        pw.close();
    }

//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        MuenService muenService=new MuenService();
//
//        ObjectMapper om=new ObjectMapper();
//        List<Muen> list=muenService.select();
//        String strList=om.writeValueAsString(list);
//        PrintWriter pw=response.getWriter();
//        pw.write(strList);
//        pw.close();
//    }

//    public static void main(String[] args) {
//        MuenService muenService=new MuenService();
//        List<Muen> list=muenService.select();
//        for (Muen list1:list){
//            System.out.println(list1);
//        }
//    }
}

