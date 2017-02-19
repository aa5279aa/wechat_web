package com.wechatweb.service;

import com.wechatweb.entitiy.EventModel;
import com.wechatweb.entitiy.receiveevent.BaseReceiveEventModel;
import com.wechatweb.entitiy.receivemsg.BaseReceiveMsgModel;
import com.wechatweb.entitiy.receivemsg.ImageReceiveMsgModel;
import com.wechatweb.entitiy.receivemsg.LinkReceiveMsgModel;
import com.wechatweb.entitiy.receivemsg.TextReceiveMsgModel;
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


    public EventModel readXML(InputStream inputStream) {
        EventModel eventEntity = new EventModel();
        try {
            Document read = reader.read(inputStream);
            Element node = read.getRootElement();
            eventEntity = parser2Entity(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eventEntity;
    }

    public EventModel parser2Entity(Element node) {
        //解析类型
        EventModel eventEntity = pareserType(node);

        //解析数据类型
        if (eventEntity.mEventType == EventModel.EVENTTYPE_MSG) {
            BaseReceiveMsgModel entity = parserMessage(eventEntity, node);
            eventEntity.msgEntity = entity;
        } else if (eventEntity.mEventType == EventModel.EVENTTYPE_EVENT) {
            BaseReceiveEventModel entity = parserEvent(eventEntity, node);
            eventEntity.eventEntity =entity;
        }
        return eventEntity;
    }

    public EventModel pareserType(Element node) {
        Element msgTypeElement = node.element("MsgType");
        if (msgTypeElement == null || !msgTypeElement.hasContent()) {
            return null;
        }
        String msgTypeStr = "";
        msgTypeStr = msgTypeElement.getText();
        EventModel eventEntity = new EventModel();

        //信息类型
        if (msgTypeStr.equals("text")) {
            eventEntity.mEventType = EventModel.EVENTTYPE_MSG;
            eventEntity.mSubType = BaseReceiveMsgModel.MSG_TYPE_TEXT;
        } else if (msgTypeStr.equals("image")) {
            eventEntity.mEventType = EventModel.EVENTTYPE_MSG;
            eventEntity.mSubType = BaseReceiveMsgModel.MSG_TYPE_IMAGE;
        } else if (msgTypeStr.equals("voice")) {
            eventEntity.mEventType = EventModel.EVENTTYPE_MSG;
            eventEntity.mSubType = BaseReceiveMsgModel.MSG_TYPE_VOICE;
        } else if (msgTypeStr.equals("video")) {
            eventEntity.mEventType = EventModel.EVENTTYPE_MSG;
            eventEntity.mSubType = BaseReceiveMsgModel.MSG_TYPE_VIDEO;
        } else if (msgTypeStr.equals("shortvideo")) {
            eventEntity.mEventType = EventModel.EVENTTYPE_MSG;
            eventEntity.mSubType = BaseReceiveMsgModel.MSG_TYPE_SHORTVIDEO;
        } else if (msgTypeStr.equals("location")) {
            eventEntity.mEventType = EventModel.EVENTTYPE_MSG;
            eventEntity.mSubType = BaseReceiveMsgModel.MSG_TYPE_LOCATION;
        } else if (msgTypeStr.equals("link")) {
            eventEntity.mEventType = EventModel.EVENTTYPE_MSG;
            eventEntity.mSubType = BaseReceiveMsgModel.MSG_TYPE_LINK;
        }
        if (eventEntity.mEventType > 0) {
            return eventEntity;
        }

        //事件类型
        if (msgTypeStr.equals("event")) {

        }
        return eventEntity;
    }

    public BaseReceiveEventModel parserEvent(EventModel eventEntity, Element node){
        BaseReceiveEventModel event=new BaseReceiveEventModel();
        return event;
    }

    private BaseReceiveMsgModel parserMessage(EventModel eventEntity, Element node) {
        BaseReceiveMsgModel baseReceiveMsgEntity = new BaseReceiveMsgModel();
        baseReceiveMsgEntity.mMsgType = eventEntity.mSubType;
        if (eventEntity.mSubType == BaseReceiveMsgModel.MSG_TYPE_TEXT) {
            Element contentElement = node.element("Content");
            String content = contentElement.getText();
            TextReceiveMsgModel entity = new TextReceiveMsgModel();
            entity.mContent = content;
            baseReceiveMsgEntity = entity;
        } else if (eventEntity.mSubType == BaseReceiveMsgModel.MSG_TYPE_IMAGE) {
            Element picUrlElement = node.element("PicUrl");
            Element mediaIdElement = node.element("MediaId");
            String picUrl = picUrlElement.getText();
            String mediaId = mediaIdElement.getText();
            ImageReceiveMsgModel entity = new ImageReceiveMsgModel();
            entity.mPicUrl = picUrl;
            entity.mMediaId = mediaId;
            baseReceiveMsgEntity = entity;
        } else if (eventEntity.mSubType == BaseReceiveMsgModel.MSG_TYPE_VOICE) {
        } else if (eventEntity.mSubType == BaseReceiveMsgModel.MSG_TYPE_VIDEO) {
        } else if (eventEntity.mSubType == BaseReceiveMsgModel.MSG_TYPE_SHORTVIDEO) {
        } else if (eventEntity.mSubType == BaseReceiveMsgModel.MSG_TYPE_LOCATION) {
        } else if (eventEntity.mSubType == BaseReceiveMsgModel.MSG_TYPE_LINK) {
            Element titleElement = node.element("Title");
            Element descriptionElement = node.element("Description");
            Element urlElement = node.element("Url");
            String title = titleElement.getText();
            String description = descriptionElement.getText();
            String url = urlElement.getText();
            LinkReceiveMsgModel entity = new LinkReceiveMsgModel();
            entity.mTitle = title;
            entity.mDescription = description;
            entity.mUrl = url;
            baseReceiveMsgEntity = entity;
        }
        parserBasePart(baseReceiveMsgEntity, node);
        return baseReceiveMsgEntity;
    }

    public void parserBasePart(BaseReceiveMsgModel entity, Element node) {
        Element toUserName = node.element("ToUserName");
        Element fromUserName = node.element("FromUserName");
        Element createTime = node.element("CreateTime");
        Element msgId = node.element("MsgId");
        if (toUserName != null && toUserName.hasContent()) {
            entity.mToUserName = toUserName.getText();
        }
        if (fromUserName != null && fromUserName.hasContent()) {
            entity.mFromUserName = fromUserName.getText();
        }
        if (createTime != null && createTime.hasContent()) {
            try {
                entity.mCreateTime = Long.parseLong(createTime.getTextTrim());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (msgId != null && msgId.hasContent()) {
            entity.mMsgId = msgId.getText();
        }
    }


}
