package com.cjj.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
//所有的servlet界面都进入到此界面
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  /user/list   /user/add   /user/delete
        String uri=request.getRequestURI();
        String [] strArr=uri.split("/");
        //得到list、add、delete
        String method=strArr[strArr.length-1];

//        UserServlet userServlet = new UserServlet();
        //DeptServlet deptServlet=new DeptServlet();

        //获取字节码对象文件
//        Class c=userServlet.getClass();

        //这里的this不是BaseServlet，谁调用service()就是谁
        //重点理解这里的this
        Class clazz = this.getClass();

        try {
            //通过反射获取指定的方法
            Method method1=clazz.getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            //暴力反射
            method1.setAccessible(true);
            //执行方法
            method1.invoke(this,request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
