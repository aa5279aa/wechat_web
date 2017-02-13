package com.wechatweb.service;

import com.sun.xml.internal.ws.util.StringUtils;
import com.wechatweb.entitiy.receivemsg.BaseReceiveMsgEntity;
import com.wechatweb.entitiy.receivemsg.ImageReceiveMsgEntity;
import com.wechatweb.entitiy.receivemsg.LinkReceiveMsgEntity;
import com.wechatweb.entitiy.receivemsg.TextReceiveMsgEntity;
import com.wechatweb.util.StringUtil;
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
            msgEntity = parser2Entity(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgEntity;
    }

    public BaseReceiveMsgEntity parser2Entity(Element node) {
        BaseReceiveMsgEntity entity = parserTypePart(node);
        parserBasePart(node, entity);
        return entity;
    }

    private BaseReceiveMsgEntity parserTypePart(Element node) {
        Element msgTypeElement = node.element("MsgType");
        String msgTypeStr = "";
        if (msgTypeElement.hasContent()) {
            try {
                msgTypeStr = msgTypeElement.getText();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BaseReceiveMsgEntity baseReceiveMsgEntity = new BaseReceiveMsgEntity();
        if (StringUtil.isBlank(msgTypeStr)) {
            baseReceiveMsgEntity.mMsgType = BaseReceiveMsgEntity.MSG_TYPE_NONE;
            return baseReceiveMsgEntity;
        }
        if (msgTypeStr.equals("text")) {
            Element contentElement = node.element("Content");
            String content = contentElement.getText();
            TextReceiveMsgEntity entity = new TextReceiveMsgEntity();
            entity.mContent = content;
            baseReceiveMsgEntity = entity;
            baseReceiveMsgEntity.mMsgType = BaseReceiveMsgEntity.MSG_TYPE_TEXT;
        } else if (msgTypeStr.equals("image")) {
            Element picUrlElement = node.element("PicUrl");
            Element mediaIdElement = node.element("MediaId");
            String picUrl = picUrlElement.getText();
            String mediaId = mediaIdElement.getText();
            ImageReceiveMsgEntity entity = new ImageReceiveMsgEntity();
            entity.mPicUrl = picUrl;
            entity.mMediaId = mediaId;
            baseReceiveMsgEntity = entity;
            baseReceiveMsgEntity.mMsgType = BaseReceiveMsgEntity.MSG_TYPE_IMAGE;
        } else if (msgTypeStr.equals("voice")) {
            baseReceiveMsgEntity.mMsgType = BaseReceiveMsgEntity.MSG_TYPE_VOICE;
        } else if (msgTypeStr.equals("video")) {
            baseReceiveMsgEntity.mMsgType = BaseReceiveMsgEntity.MSG_TYPE_VIDEO;
        } else if (msgTypeStr.equals("shortvideo")) {
            baseReceiveMsgEntity.mMsgType = BaseReceiveMsgEntity.MSG_TYPE_SHORTVIDEO;
        } else if (msgTypeStr.equals("location")) {
            baseReceiveMsgEntity.mMsgType = BaseReceiveMsgEntity.MSG_TYPE_LOCATION;
        } else if (msgTypeStr.equals("link")) {
            Element titleElement = node.element("Title");
            Element descriptionElement = node.element("Description");
            Element urlElement = node.element("Url");
            String title = titleElement.getText();
            String description = descriptionElement.getText();
            String url = urlElement.getText();
            LinkReceiveMsgEntity entity = new LinkReceiveMsgEntity();
            entity.mTitle = title;
            entity.mDescription = description;
            entity.mUrl = url;
            baseReceiveMsgEntity = entity;
        }
        return baseReceiveMsgEntity;
    }

    public void parserBasePart(Element node, BaseReceiveMsgEntity entity) {
        Element toUserName = node.element("ToUserName");
        Element fromUserName = node.element("FromUserName");
        Element createTime = node.element("CreateTime");
        Element msgId = node.element("MsgId");
        if (toUserName.hasContent()) {
            entity.mToUserName = toUserName.getText();
        }
        if (fromUserName.hasContent()) {
            entity.mFromUserName = fromUserName.getText();
        }
        if (createTime.hasContent()) {
            try {
                entity.mCreateTime = Long.parseLong(createTime.getTextTrim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (msgId.hasContent()) {
            entity.mMsgId = msgId.getText();
        }
    }


}
