package com.cjj.controller;

import com.cjj.entity.Dept;
import com.cjj.service.DeptService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet {
    private static DeptService deptService = new DeptService();
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper om = new ObjectMapper();
        List<Dept> list = deptService.list();
        String strList = om.writeValueAsString(list);
        PrintWriter pw = response.getWriter();
        pw.write(strList);
        pw.close();
    }

//    public static void main(String[] args) {
//        List<Dept> list = deptService.list();
//        for (Dept d:list){
//            System.out.println(d);
//        }
//    }
}
