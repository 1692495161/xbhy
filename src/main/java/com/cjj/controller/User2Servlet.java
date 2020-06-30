package com.cjj.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
//@WebServlet("/user/*")
public class User2Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  /user/list   /user/add   /user/delete
        String uri = request.getRequestURI();
        if (uri.endsWith("list")) {
            list(request, response);
        } else if (uri.endsWith("add")) {
            add(request, response);
        } else if (uri.endsWith("delete")) {
            add(request, response);
        }

    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/user/user.jsp").forward(request, response);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
