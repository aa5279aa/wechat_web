package com.wechatweb.entitiy.menu;

import java.util.ArrayList;

/**
 * Created by xiangleiliu on 2017/2/19.
 */
public class MenuModel {

    public static final int ClickTypeClick = 1;//产生点击事件，以event的方式传给开发者
    public static final int ClickTypeView = 2;//跳转URL
    public static final int ClickTypeScanCodePush = 3;//调用扫一扫工具
    public static final int ClickTypeScanCdoeWaitMsg = 4;//调用扫一扫工具
    public static final int ClickTypeScanCdoeMediaId = 9;//推送素材给用户
    public static final int ClickTypeScanCdoeViewLimited = 10;//跳转图文消息URL

    public String id = "";//唯一的按钮标识   1_0 就代表父节点第一个  1_1就代表第一个父节点下的第一个子节点
    public int mLevel;//级别

    public String type;//类型
    public String name;//展示的文本
    public String key;
    public String url;
    public ArrayList<MenuModel> sub_button = new ArrayList<>();


    @Override
    public String toString() {
        return super.toString();
    }
}
