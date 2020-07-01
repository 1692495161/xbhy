package com.cjj.controller;

import com.cjj.constant.SysConstant;
import com.cjj.utils.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author cjj
 * @date 2020/6/28
 * @description
 */
@WebServlet("/email/*")
public class EmailServlet extends BaseServlet {

    protected void email(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        HttpSession session = request.getSession();
        //返回一个数据给前端
        PrintWriter pw = response.getWriter();
        if (!(email == null && "".equals(email))) {
            //获取随机验证码
            String randomCode = Math.random() + "";
            randomCode = randomCode.substring(randomCode.length() - 5, randomCode.length() - 1);
            //调用发送邮件的方法
            Boolean b = EmailUtil.sendEmail(email, randomCode);
            //将验证码存入session中
            if (b) {
                session.setAttribute(SysConstant.SESSION_CODE, randomCode);
                session.setMaxInactiveInterval(60);
                pw.write("1");
                return;
            }
        }
        pw.write("0");
    }

//    public static void main(String[] args) {
//        int i = (int) (Math.random() * 10000 + 1);
//        System.out.println(i);
//    }
}
