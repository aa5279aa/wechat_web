package com.wechatweb.service;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by xiangleiliu on 2017/2/14.
 */
public class SendService {

    public void createTextMessage(OutputStream os,String ToUserName,String FromUserName, String mContent) throws IOException {
        Element root = DocumentHelper.createElement("xml");
        Document document = DocumentHelper.createDocument(root);
        //给根节点添加孩子节点
        root.addElement("ToUserName").addText(ToUserName);
        root.addElement("FromUserName").addText(FromUserName);
        root.addElement("CreateTime").addText(String.valueOf(System.currentTimeMillis()));
        root.addElement("MsgType").addText("text");
        root.addElement("Content").addText(mContent);

        //把生成的xml文档存放在硬盘上  true代表是否换行
        OutputFormat format = new OutputFormat("    ", true);
        format.setEncoding("UTF-8");//设置编码格式
        XMLWriter xmlWriter = new XMLWriter(os, format);

        xmlWriter.write(document);
        xmlWriter.close();
    }
}
