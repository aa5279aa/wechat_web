package com.wechatweb.base;

import com.wechatweb.service.SendService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by xiangleiliu on 2017/2/19.
 */
public class BaseSendServlet extends HttpServlet {
    SendService sendService = new SendService();

    public BaseSendServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String input_userid = request.getParameter("input_userid");
        String input_msg = request.getParameter("input_msg");

        OutputStream os = null;
        sendService.createTextMessage(os, "", "", input_msg);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
