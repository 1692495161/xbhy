package com.cjj.controller;

import com.cjj.entity.Dept;
import com.cjj.entity.Page;
import com.cjj.entity.User;
import com.cjj.service.DeptService;
import com.cjj.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private static UserService userService = new UserService();
    private static DeptService deptService = new DeptService();

    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //根据姓名模糊查询
        String username = request.getParameter("username");
//        if (username == null) {
//            username = "";
//        }
        username = username == null ? "" : username;

        //根据姓名以及性别
        String sex = request.getParameter("select");
        if (StringUtils.isEmpty(sex)) {
            sex = "-1";
        }

        String pageStr = request.getParameter("page");

        Page page = userService.listAll(username, sex, pageStr);
        request.setAttribute("page", page);
        request.setAttribute("username", username);
        request.setAttribute("sex", sex);
        request.getRequestDispatcher("/jsp/user/user.jsp").forward(request, response);
    }

//        protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //根据姓名模糊查询
//        String username = request.getParameter("username");
//        if (username==null){
//            username="";
//        }
//        //根据姓名以及性别
//        String sex=request.getParameter("select");
//        if (StringUtils.isEmpty(sex)){
//            sex="-1";
//        }
//
//        //分页
//        String pageStr = request.getParameter("page");
//        //获取总记录条数
//        Integer count = userService.getCount(username,sex);
//        //获取分页总数
//        Integer pageCount = count % 5 == 0 ? count / 5 : count / 5 + 1;
//        //获取页数，第一次进来设页数为1
//        Integer page = 1;
//        if (!StringUtils.isEmpty(pageStr)) {
//            page = Integer.valueOf(pageStr);
//            if (page <= 0) {
//                page = 1;
//            }
//            if (page > pageCount) {
//                page = pageCount;
//            }
//        }
//
//        List<User> list = userService.list(username,sex,page);
//        request.setAttribute("list", list);
//        request.setAttribute("username",username);
//        request.setAttribute("sex",sex);
//        request.setAttribute("count", count);
//        request.setAttribute("pageCount", pageCount);
//        request.setAttribute("page", page);
//        request.getRequestDispatcher("/jsp/user/user.jsp").forward(request, response);
//    }
//
    protected void findName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("username");
        User user = userService.findName(name);
        PrintWriter pw = response.getWriter();
        if (user != null) {
            pw.write("1");
            pw.close();
        } else {
            pw.write("0");
            pw.close();
        }
    }

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userService.add(user);

        response.sendRedirect("/user/list");

    }

    protected void getUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //回显数据
        String id = request.getParameter("id");
        User user = userService.getUserById(Integer.valueOf(id));
        request.setAttribute("user", user);
        //回显部门信息
        List<Dept> depts = deptService.list();
        request.setAttribute("dept", depts);
        request.getRequestDispatcher("/jsp/user/update.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        userService.update(user);
        response.sendRedirect("/user/list");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        userService.delete(Integer.valueOf(id));
        response.sendRedirect("/user/list");
    }
}
