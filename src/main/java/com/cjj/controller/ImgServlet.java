package com.cjj.controller;

import com.cjj.constant.SysConstant;
import com.cjj.entity.User;
import com.cjj.service.UserService;
import com.cjj.utils.ImgCodeUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author cjj
 * @date 2020/6/29
 * @description
 */
@WebServlet("/img/*")
public class ImgServlet extends BaseServlet {
    private UserService userService = new UserService();

    /*
     *@date 2020/6/29
     *@param [request, response]
     *@return void
     *@description 获取验证码
     */
    protected void getCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        FileInputStream fis=new FileInputStream("C:\\Users\\admin\\Desktop\\1.PNG");

        HttpSession session = request.getSession();

        ImgCodeUtil imgCodeUtil = new ImgCodeUtil();
        //获取需要传递的code的图片
        BufferedImage image = imgCodeUtil.getImage();
        //获取code
        String codeText = imgCodeUtil.getText();
        //将获取的code加入session
        session.setAttribute(SysConstant.SESSION_LOGIN_CODE, codeText);
        //获取一个输出流
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "jpeg", os);
        os.flush();
        os.close();
    }


    protected void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        //为解析类提供配置信息 创建文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析类的实例 传入工厂类获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置文件最大解析大小(单位：字节)
        sfu.setFileSizeMax(1024 * 1024 * 2);
        //后缀名变量
        String suffix = "";
        try {
            List<FileItem> items = sfu.parseRequest(request);
            for (int i = 0; i < items.size(); i++) {
                FileItem item = items.get(i);
                //isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    String name = item.getName();
                    //获取文件的后缀名
                    String[] names = name.split("\\.");
                    name = names[names.length - 1];

                    //构造文件路径(保存到数据库的路径)
                    //时间戳
                    suffix = System.currentTimeMillis() + "." + name;
                    String path = SysConstant.FILE_PREFIX + suffix;

                    //UUID
                    String path2 = SysConstant.FILE_PREFIX + UUID.randomUUID().toString().replace("-", "") + "." + name;

                    //把文件上传到服务器
                    File file = new File(path);
                    //先判断服务器上是否存在该文件
                    if (!file.exists()) {
                        try {
                            //将文件写出到指定磁盘（即保存图片的服务器）
                            item.write(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
            //获取session中的登陆信息
            User user = (User) request.getSession().getAttribute(SysConstant.SESSION_LOGIN);
            //将获取到用户信息，将其头像路径保存到数据库
            userService.updatePic(user.getId(), suffix);

            out.write("1");
        } catch (FileUploadException e) {
            e.printStackTrace();
            out.write("0");
        }
    }

    protected void getHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.getUserById(Integer.valueOf(id));
        //获取数据库的路径
        String path = SysConstant.FILE_PREFIX + user.getPic();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
        OutputStream os = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            os.write(bytes, 0, len);
        }
        os.flush();
        if (os != null) {
            os.close();
        }
        if (bis != null) {
            bis.close();
        }
    }

    protected void getHead2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        OutputStream os = response.getOutputStream();
        User user = (User) session.getAttribute(SysConstant.SESSION_LOGIN);
        String pic = user.getPic();
        if (pic.contains("http")) {
            os.write(pic.getBytes());
        } else {
            Integer id = user.getId();
            User user1 = userService.getUserById(id);
            String path = SysConstant.FILE_PREFIX + user1.getPic();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            os.flush();
            if (os != null) {
                os.close();
            }
            if (bis != null) {
                bis.close();
            }
        }
    }
}
