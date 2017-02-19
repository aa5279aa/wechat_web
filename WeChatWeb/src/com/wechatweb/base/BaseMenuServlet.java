package com.wechatweb.base;

import com.wechatweb.entitiy.menu.MenuModel;
import com.wechatweb.service.Logger;
import com.wechatweb.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by xiangleiliu on 2017/2/19.
 */
public class BaseMenuServlet extends HttpServlet {
    MenuService menuService;

    public BaseMenuServlet() {
        super();
        menuService = new MenuService();
    }

    //http://123.206.182.232/WeChatWeb/weidumenu?action=add&parentid=0&key=caidan&type=view&name=菜单&url=http://www.soso.com/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        //收到提交，缓存一下，发给微信服务器。每次选查询一下微信的，然后在返回
        String bu = getBu(req);

        String action = req.getParameter("action");//操作类型，加 减 add or remove
        String parentid = req.getParameter("parentid");
        String key = req.getParameter("key");
        String type = req.getParameter("type");
        String name = req.getParameter("name");
        String url = req.getParameter("url");

        //查询目前菜单
        List<MenuModel> selectMenuModels = menuService.requestSelectMenuList(bu);
        Logger.getLogger().showMessage("selectMenuModels success");

        //构建节点
        MenuModel menuModel = menuService.createMenuModel(key, type, name, url);
        Logger.getLogger().showMessage("menuModel" + menuModel.toString());

        //插入或删除到目前节点列中
        List<MenuModel> menuModels = menuService.actionMenu(action, selectMenuModels, parentid, menuModel);
        Logger.getLogger().showMessage("actionMenu" + menuModels.size());

        //请求微信修改
        boolean isSuccess = menuService.requestUpdateMenuList(bu, menuModels);
        Logger.getLogger().showMessage("requestUpdateMenuList" + isSuccess);

        if (isSuccess) {
            resp.getWriter().write("success");
        } else {
            resp.getWriter().write("fail");
        }
        resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        doGet(req, resp);
    }

    public String getBu(HttpServletRequest req) {
        String requestUrl = req.getRequestURL().toString();
        String substring = requestUrl.substring(requestUrl.lastIndexOf("/")).replaceAll("/", "");
        if (substring.contains("_")) {
            return substring.substring(0, substring.indexOf("_"));
        }
        return substring;
    }
}
