package com.wechatweb.base;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiangleiliu on 2017/2/12.
 */
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //xml数据解析

        //分析fromUser

        //分析action

        //处理

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //验证部分
        request.getParameter("signature");
        request.getParameter("timestamp");
        request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        response.getWriter().write(echostr);
        response.getWriter().flush();
    }
}
