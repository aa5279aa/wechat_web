package com.wechatweb.entitiy;

import com.wechatweb.entitiy.receiveevent.BaseReceiveEventModel;
import com.wechatweb.entitiy.receivemsg.BaseReceiveMsgModel;

/**
 * Created by xiangleiliu on 2017/2/14.
 */
public class EventModel {
    public static final int EVENTTYPE_MSG = 1;
    public static final int EVENTTYPE_EVENT = 2;

    public int mEventType = 0;//1信息，2事件，
    public int mSubType = 0;//子类型

    public BaseReceiveMsgModel msgEntity;
    public BaseReceiveEventModel eventEntity;
}
