package com.cjj.controller;

import com.alibaba.fastjson.JSONObject;
import com.cjj.constant.SysConstant;
import com.cjj.entity.User;
import com.cjj.service.OtherService;
import com.cjj.service.UserService;
import com.cjj.utils.OtherUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

/**
 * @author cjj
 * @date 2020/6/30
 * @description
 */
@WebServlet("/otherLogin/*")
public class WeChatServlet extends BaseServlet {
    private OtherService otherService=new OtherService();
    private UserService userService=new UserService();

    //微信回调函数
    protected void weChat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties prop = new Properties();
        //同通过类加载器获取资源文件
        prop.load(WeChatServlet.class.getClassLoader().getResourceAsStream("weChat.properties"));
        String appId = prop.getProperty("appId");
        //微信授权成功后的回调地址
        String redirect = prop.getProperty("redirect");
        //Step1：获取Authorization Code（请求认证）
        String url = "https://open.weixin.qq.com/connect/qrconnect?response_type=code" +
                "&appid=" + appId +
                "&redirect_uri=" + URLEncoder.encode(redirect) +
                "&scope=snsapi_login";
        // 重定向到微信登录指定的地址进行微信登录授权,授权成功后返回code
        response.sendRedirect(url);
    }

    //处理微信的回调函数
    protected void wx_login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        //加载配置文件
        Properties prop=new Properties();
        //类加载器
        prop.load(WeChatServlet.class.getClassLoader().getResourceAsStream("weChat.properties"));
        //获取回调函数的code值
        String code=request.getParameter("code");
        String appId = prop.getProperty("appId");
        String appSecret=prop.getProperty("wx.AppSecret");

        //进行认证
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + appId +
                "&secret=" + appSecret +
                "&code=" + code +
                "&grant_type=authorization_code";
        // 获取AccessToken、openid等数据
        JSONObject authInfo=OtherUtil.auth(url);
        System.out.println(authInfo);

        //获取微信用户信息
        url = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + authInfo.getString("access_token") +
                "&openid=" + authInfo.getString("openid");

        JSONObject userInfo=OtherUtil.getUserInfo(url);
        System.out.println(userInfo);

        //根据微信的openid查询该用户是否第一次使用微信登陆
        User user=otherService.list(userInfo.getString("openid"));
        if (user==null){
            System.out.println("第一次");
            // 说明该用户是第一次使用微信登录
            user=new User();
            //设置用户随机名
            user.setUsername(String.valueOf(System.currentTimeMillis()));
            //设置用户性别
            user.setSex(userInfo.getString("sex"));
            //设置头像
            user.setPic(userInfo.getString("headimgurl"));
            //设置注册时间
            user.setRegisterTime(new Date());
            //设置用户的真实昵称
            user.setRealName(userInfo.getString("nickname"));
            //设置用户微信的openid
            user.setWxOpenid(userInfo.getString("openid"));
            //新增加一个用户
            userService.add(user);
        }
        //将用户信息加入session中
        session.setAttribute(SysConstant.SESSION_LOGIN,user);
//        response.sendRedirect("/jsp/common/main.jsp");
        request.getRequestDispatcher("/jsp/common/main.jsp").forward(request,response);
    }
}
