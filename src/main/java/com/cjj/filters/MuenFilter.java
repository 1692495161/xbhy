package com.cjj.filters;

import com.cjj.constant.SysConstant;
import com.cjj.entity.User;
import com.cjj.enums.SysEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author cjj
 * @date 2020/6/22
 * @description
 */
@WebFilter("/*")
public class MuenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=UTF-8");

        ObjectMapper om = new ObjectMapper();
        HttpSession session = request.getSession();
        //获取session中登录的用户信息
        Object object = session.getAttribute(SysConstant.SESSION_LOGIN);

        String uri = request.getRequestURI();
        if (uri.endsWith("/index.jsp")) {
            //获取cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String cookieName = cookie.getName();

                    //判断cookie的值是否和枚举存储的一致
                    if (SysEnum.COOKIE_NAME.getValue().equals(cookieName)) {
                        String cookieValue = cookie.getValue();
                        cookieValue = URLDecoder.decode(cookieValue);
                        User user = om.readValue(cookieValue, User.class);
                        //
                        session.setAttribute(SysConstant.SESSION_LOGIN, user);
                        filterChain.doFilter(request, response);

                        //如果cookie有值，则直接跳转到成功界面
                        request.getRequestDispatcher("/jsp/common/main.jsp").forward(request, response);
                        return;
                    }
                }
            }
        } else if (uri.endsWith("/")
                || uri.endsWith("/login/login")
                || uri.endsWith("/forget.jsp")
                || uri.endsWith("/email/email")
                || uri.endsWith("/img/getCode")
                || uri.endsWith("/img/upload")
                || uri.endsWith("/img/getHead")
                || uri.endsWith("/img/getHead2")
                || uri.contains("img")
                || uri.contains("doc")
                || uri.contains("static")
                || uri.endsWith("/otherLogin/weChat")
                || uri.endsWith("/otherLogin/wx_login")
                || uri.endsWith("/forget/updateForget")) {
            //直接放行
        } else {
            if (object == null) {
                //如果session中为空，则是非法登录
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                //表明session中有值
            }
        }
        filterChain.doFilter(request, response);
    }
}
