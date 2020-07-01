package com.cjj.controller;

import com.cjj.service.PoiService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author cjj
 * @date 2020/7/1
 * @description
 */
@WebServlet("/poi/*")
public class PoiServlet extends BaseServlet {
    private PoiService poiService=new PoiService();
    protected void exportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("员工信息.xlsx".getBytes("utf-8"), "iso-8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");

        String username=request.getParameter("username");
        String sex=request.getParameter("sex");
        String strPage=request.getParameter("page");

        Workbook wb=poiService.export(username,sex,strPage);
        OutputStream os=response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }
}
