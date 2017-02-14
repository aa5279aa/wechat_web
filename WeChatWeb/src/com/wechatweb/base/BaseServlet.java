package com.wechatweb.base;

import com.wechatweb.entitiy.EventEntity;
import com.wechatweb.entitiy.receiveevent.BaseReceiveEventEntity;
import com.wechatweb.entitiy.receivemsg.BaseReceiveMsgEntity;
import com.wechatweb.entitiy.receivemsg.TextReceiveMsgEntity;
import com.wechatweb.service.Logger;
import com.wechatweb.service.ReadService;
import com.wechatweb.service.SendService;
import com.wechatweb.util.IOHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by xiangleiliu on 2017/2/12.
 */
public class BaseServlet extends HttpServlet {

    ReadService readService = new ReadService();
    SendService sendService = new SendService();
    Logger logger = Logger.getLogger();

    public BaseServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        logger.showMessage("doPost+success");
        //xml数据解析
        ServletInputStream requestInputStream = request.getInputStream();
        InputStream inputStream = null;
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String remoteHost = request.getRemoteHost();
        if (remoteHost.equals("localhost") || remoteHost.equals("127.0.0.1")) {
            List<FileItem> fileItems = null;
            try {
                fileItems = upload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            if (fileItems.size() > 0) {
                inputStream = fileItems.get(0).getInputStream();
            }
        }
        if (inputStream == null) {
            inputStream = requestInputStream;
        }

        EventEntity eventEntity = readService.readXML(inputStream);
        if (eventEntity.mEventType == EventEntity.EVENTTYPE_MSG) {
            BaseReceiveMsgEntity msgEntity = eventEntity.msgEntity;
            if (msgEntity instanceof TextReceiveMsgEntity) {
                logger.showMessage("MSGTYPE:" + msgEntity.mMsgType + "，fromUserName" + msgEntity.mFromUserName + "，content:" + ((TextReceiveMsgEntity) msgEntity).mContent);
                sendService.createTextMessage(response.getOutputStream(),msgEntity.mFromUserName, msgEntity.mToUserName, "成功收到消息：" + ((TextReceiveMsgEntity) msgEntity).mContent);
                try {
//                    IOHelper.writeToOutpusStream(response.getOutputStream(), in);
                } catch (Exception e) {
                    response.getWriter().write("success");
                }
            } else {
                logger.showMessage("MSGTYPE:" + msgEntity.mMsgType + "，fromUserName" + msgEntity.mFromUserName + "，不支持的消息类型");
                sendService.createTextMessage(response.getOutputStream(),msgEntity.mFromUserName, msgEntity.mToUserName, "不支持的消息类型：");
                try {
//                    IOHelper.writeToOutpusStream(response.getOutputStream(), in);
                } catch (Exception e) {
                    response.getWriter().write("success");
                }
            }
        }
        response.getWriter().flush();
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
