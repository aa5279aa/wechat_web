<%--
  Created by IntelliJ IDEA.
  User: xiangleiliu
  Date: 2017/2/12
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title> - 在线请求测试工具 - </title>
    <style type="text/css">
        body {
            font-family: Tahoma, Verdana, Arial, Helvetica, sans-serif;
            font-size: 15px;
        }

        p, h1, form, button {
            border: 0;
            margin: 0;
            padding: 0;
        }

        .spacer {
            clear: both;
            height: 1px;
        }

        /* ———– My Form ———– */
        .myform {
            margin: 0 auto;
            width: 600px;
            padding: 14px;
            border: solid 2px #b7ddf2;
            background: #ebf4fb;
        }

        .myform h1 {
            font-size: 16px;
            font-weight: bold;
            margin-bottom: 8px;
        }

        .myform p {
            font-size: 12px;
            color: #666666;
            margin-bottom: 20px;
            border-bottom: solid 1px #b7ddf2;
            padding-bottom: 10px;
        }

        .myform label {
            display: block;
            font-weight: bold;
            text-align: right;
            width: 140px;
            float: left;
        }

        .myform .small {
            color: #666666;
            display: block;
            font-size: 11px;
            font-weight: normal;
            text-align: right;
            width: 140px;
        }

        .myform input {
            float: left;
            font-size: 15px;
            padding: 4px 2px;
            border: solid 1px #aacfe4;
            width: 200px;
            margin: 2px 0 20px 10px;
        }

        .myform .sub {
            clear: both;
            margin-left: 150px;
            width: 120px;
            height: 32px;
            line-height: 20px;
            border: 1px solid #8b9c56;
            text-align: center;
            color: #336600;
            font-size: 15px;
            font-weight: bold;
            cursor: pointer;
        }

        .red {
            color: #ff0000;
        }

        .blue {
            color: #0000FF;
        }
    </style>
</head>
<body>
<div id="stylized" class="myform">
    <form name="form" method="post" action="weidutest" enctype="multipart/form-data">
        <h1 style="text-align:center">在线收消息测试工具</h1>
        <label>
            <span class="small">选择文件提交</span>
        </label>
        <input type="file" name="sourcecode" id="inputvalue" value=""/>

        <input class="sub" type="submit" value="提交"/>

        <div class="spacer"></div>
    </form>
</div>

<div class="spacer"></div>

<div id="stylized_2" class="myform">
    <form name="form" method="post" action="weidu_send" enctype="multipart/form-data">
        <h1 style="text-align:center">在线发消息测试工具</h1>
        <table>
            <tr>
                <td>
                    <label>
                        <span class="small">请输入接收用户Id</span>
                    </label>
                    <input type="text" name="userId" id="input_userid" value=""/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>
                        <span class="small">请输入消息内容</span>
                    </label>
                    <input type="text" name="message" id="input_msg" value=""/>
                </td>
            </tr>
            <tr>
                <td>
                    <input class="sub" type="submit" value="提交"/>
                </td>
            </tr>
        </table>
        <div class="spacer"></div>
    </form>
</div>
</body>
</html>
