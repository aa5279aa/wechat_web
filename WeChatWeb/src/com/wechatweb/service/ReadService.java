package com.wechatweb.service;

import com.wechatweb.entitiy.receivemsg.BaseReceiveMsgEntity;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * Created by xiangleiliu on 2017/2/13.
 */
public class ReadService {

    SAXReader reader = new SAXReader();

    public ReadService() {

    }


    public BaseReceiveMsgEntity readXML(InputStream inputStream) {
        BaseReceiveMsgEntity msgEntity = new BaseReceiveMsgEntity();
        try {
            Document read = reader.read(inputStream);
            Element node = read.getRootElement();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgEntity;
    }
}
