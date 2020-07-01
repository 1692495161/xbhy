package com.cjj.controller;

import com.cjj.entity.Meet;
import com.cjj.service.MeetService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author cjj
 * @date 2020/6/30
 * @description
 */
@WebServlet("/meet/*")
public class MeetServlet extends BaseServlet {
    private MeetService meetService = new MeetService();

    protected void meetList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取查询的用户名
        String name = request.getParameter("username");
        if (StringUtils.isEmpty(name)) {
            name = "";
        }
        //获取查询的会议状态
        String status = request.getParameter("status");
        if (StringUtils.isEmpty(status)) {
            status = "-1";
        }

        List<Meet> list = meetService.meetList(name, Integer.valueOf(status));
        request.setAttribute("username", name);
        request.setAttribute("list", list);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/jsp/meet/meet.jsp").forward(request, response);
    }

    /*
    *@date 2020/7/1
    *@param [request, response]
    *@return void
    *@description 新增会议
    */
    protected void meetAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        Meet meet = new Meet();

        try {
            BeanUtils.populate(meet, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        meetService.addMeet(meet);
        response.sendRedirect("/meet/meetList");
    }

    /*
    *@date 2020/7/1
    *@param [request, response]
    *@return void
    *@description 通过id获取对应的会议信息
    */
    protected void getMeetById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        Meet meet=meetService.getMeetById(Integer.valueOf(id));
        request.setAttribute("meet",meet);
        request.getRequestDispatcher("/jsp/meet/checkMeet.jsp").forward(request,response);
    }
}
