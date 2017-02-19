package com.wechatweb.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.BasicHttpRequest;
import com.wechatweb.entitiy.menu.MenuModel;
import com.wechatweb.util.IOHelper;
import com.wechatweb.util.UrlConfig;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangleiliu on 2017/2/19.
 */
public class MenuService {

    public MenuService() {

    }

    public List<MenuModel> requestSelectMenuList(String bu) {
        String menuReqeust = UrlConfig.getMenuSelectReqeust(bu);
        HttpPost request = new HttpPost(menuReqeust);

        List<MenuModel> menuList = new ArrayList<>();
        try {
            HttpResponse response = HttpClients.createDefault().execute(request);
            InputStream inputStream = response.getEntity().getContent();
            String s = IOHelper.fromIputStreamToString(inputStream);
            JSONObject jsonObject = JSON.parseObject(s);
            JSONObject menuJson = jsonObject.getJSONObject("menu");
            JSONArray button = menuJson.getJSONArray("button");
            for (int i = 0; i < button.size(); i++) {
                JSONObject buttonJson = button.getJSONObject(i);
                MenuModel menu = JSON.parseObject(buttonJson.toJSONString(), MenuModel.class);
                menuList.add(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuList;
    }

    public MenuModel createMenuModel(String key, String type, String name, String url) {
        MenuModel menuModel = new MenuModel();
        menuModel.key = key;
        menuModel.type = type;
        menuModel.name = name;
        menuModel.url = url;
        return menuModel;
    }

    public List<MenuModel> actionMenu(String action, List<MenuModel> selectMenuModels, String parentid, MenuModel menuModel) {
        if (action.equals("delete")) {
            return selectMenuModels;
        }
        if (parentid == null || parentid.equals("") || parentid.equals("0")) {
            menuModel.id = String.valueOf(selectMenuModels.size());
            selectMenuModels.add(menuModel);
            return selectMenuModels;
        }

        for (MenuModel menuModel1 : selectMenuModels) {
            if (!menuModel1.id.equals(parentid)) {
                continue;
            }
            menuModel.id = parentid + "_" + menuModel.sub_button.size();
            menuModel.sub_button.add(menuModel);
        }
        return selectMenuModels;
    }

    public boolean requestUpdateMenuList(String bu, List<MenuModel> menuModels) {

        String menusJson = JSON.toJSONString(menuModels);
        JSONArray buttonArray = JSON.parseArray(menusJson);
        JSONObject json = new JSONObject();
        json.put("button", buttonArray);
        String menuCreateReqeust = UrlConfig.getMenuCreateReqeust(bu);
        OutputStream out = new NullOutputStream();
        try {
            IOHelper.writerStrByCode(out, "utf-8", json.toJSONString());
            HttpEntity httpEntity = new BasicHttpEntity();
            httpEntity.writeTo(null);
            HttpPost request = new HttpPost(menuCreateReqeust);
            request.setEntity(httpEntity);
            HttpResponse response = HttpClients.createDefault().execute(request);
            InputStream content = response.getEntity().getContent();
            String s = IOHelper.fromIputStreamToString(content);
            Logger.getLogger().showMessage(s);
            if (s.equals("{\"errcode\":0,\"errmsg\":\"ok\"}")) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
