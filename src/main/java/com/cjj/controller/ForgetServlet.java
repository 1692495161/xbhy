package com.cjj.controller;

import com.cjj.constant.SysConstant;
import com.cjj.entity.User;
import com.cjj.service.UserService;
import com.cjj.utils.MdUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author cjj
 * @date 2020/6/28
 * @description
 */
@WebServlet("/forget/*")
public class ForgetServlet extends BaseServlet {
    private UserService userService=new UserService();

    /*
    *@date 2020/6/28
    *@param [request, response]
    *@return void
    *@description 修改密码
    */
    protected void updateForget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String pwd=request.getParameter("password");
        String code=request.getParameter("code");
        //获取session中的code值
        HttpSession session=request.getSession();
        //通过username判断是否存在该用户名
        User userName=userService.findName(username);
        //获取session的键
        Object obj=session.getAttribute(SysConstant.SESSION_CODE);
        if (obj!=null){
            //将输入的code值和session的值进行比较，判断是否存在该用户
            if (code.equals(obj.toString()) && userName!=null){
                userService.updateUserByName(MdUtil.md5(pwd),username);
                request.getRequestDispatcher("/index.jsp").forward(request,response);
                return;
            }
        }
        //修改失败则返回忘记修改页面
        request.getRequestDispatcher("/jsp/forget/forget.jsp").forward(request,response);
    }
}
