package com.wechatweb.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiangleiliu on 2017/2/19.
 */
public class UrlConfig {

    public static Map<String, String> TokenMap = new HashMap<>();
    public final static String MenuListSelectUrl = "https://api.weixin.qq.com/cgi-bin/menu/get?";
    public final static String MenuListCreateUrl = " https://api.weixin.qq.com/cgi-bin/menu/create?";

    static {
        TokenMap.put("weidu", "lxl301lxl");
    }

    public static String getMenuSelectReqeust(String bu) {
        return MenuListSelectUrl + "access_token=" + TokenMap.get(bu);
    }

    public static String getMenuCreateReqeust(String bu) {
        return MenuListCreateUrl + "access_token=" + TokenMap.get(bu);
    }

}
