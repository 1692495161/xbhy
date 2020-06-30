package com.cjj.controller;

import com.cjj.constant.SysConstant;
import com.cjj.entity.User;
import com.cjj.enums.SysEnum;
import com.cjj.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author cjj
 * @date 2020/6/28
 * @description
 */
@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {
    private UserService userService = new UserService();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //声明一个session
        HttpSession session = request.getSession();
        //将对象json格式化
        ObjectMapper om = new ObjectMapper();

        String username = request.getParameter("username");
        String newPwd = request.getParameter("password");
        String remember = request.getParameter("remember");
        //获取前端输入的code
        String code = request.getParameter("code");
        Object sessionCode = session.getAttribute(SysConstant.SESSION_LOGIN_CODE);

        User user = userService.checkLogin(username, newPwd);
        if (user == null || !(code.equalsIgnoreCase(sessionCode.toString())) || sessionCode == null) {
            //说明用户不存在，返回登录界面
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
            response.sendRedirect("/index.jsp");
        } else {
            //证明用户存在，存入session
            session.setAttribute(SysConstant.SESSION_LOGIN, user);
            session.setMaxInactiveInterval(30 * 60);

            //勾选记住我
            if ("1".equals(remember)) {
                //将用户信息存入Cookie
                Cookie c = new Cookie(SysEnum.COOKIE_NAME.getValue(), URLEncoder.encode(om.writeValueAsString(user), "utf-8"));
                //设置cookie的存活时间
                c.setMaxAge(7 * 24 * 60 * 60);
                c.setPath("/");
                //将cookie加入客户端
                response.addCookie(c);
            }
            request.getRequestDispatcher("/jsp/common/main.jsp").forward(request, response);
        }


    }
}
