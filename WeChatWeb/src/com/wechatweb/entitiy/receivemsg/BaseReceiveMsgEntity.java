package com.wechatweb.entitiy.receivemsg;

/**
 * Created by xiangleiliu on 2017/2/13.
 */
public class BaseReceiveMsgEntity {
    public static final int MSG_TYPE_NONE = 0;
    public static final int MSG_TYPE_TEXT = 1;
    public static final int MSG_TYPE_IMAGE = 2;
    public static final int MSG_TYPE_VOICE = 3;
    public static final int MSG_TYPE_VIDEO = 4;
    public static final int MSG_TYPE_SHORTVIDEO = 5;
    public static final int MSG_TYPE_LOCATION = 6;
    public static final int MSG_TYPE_LINK = 7;

    public String mToUserName;//开发者微信号
    public String mFromUserName;//发送方帐号（一个OpenID）
    public long mCreateTime;//消息创建时间 （整型）
    public int mMsgType;//image图片 text文本 voice语音 video视频 shortvideo小视频 location地理位置 link链接
    public String mMsgId;//消息Id

}
