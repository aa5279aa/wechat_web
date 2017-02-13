package com.wechatweb.base;

import com.wechatweb.entitiy.receivemsg.BaseReceiveMsgEntity;
import com.wechatweb.service.Logger;
import com.wechatweb.service.ReadService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiangleiliu on 2017/2/12.
 */
public class BaseServlet extends HttpServlet {

    ReadService readService = new ReadService();
    Logger logger = Logger.getLogger();

    public BaseServlet() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        logger.showMessage("doPost+success");
        //xml数据解析
        ServletInputStream inputStream = request.getInputStream();
        BaseReceiveMsgEntity msgEntity = readService.readXML(inputStream);
        //分析fromUser

        //分析action

        //处理输出报文

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //验证部分
        logger.showMessage("doGet+success");
        request.getParameter("signature");
        request.getParameter("timestamp");
        request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (echostr == null) {
            response.getWriter().write("null string");
        } else {
            response.getWriter().write(echostr);
        }
        response.getWriter().flush();
    }
}
