package com.maruifu.wechat.util;


import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

import com.maruifu.wechat.pojo.dmo.base.Article;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtil {

    /**
     * 解析微信发来的请求（XML）
     *
     * @param request
     * @return map
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        System.out.println("获取输入流");
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            System.out.println(e.getName() + "|" + e.getText());
            map.put(e.getName(), e.getText());
        }

        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * map 拼接 xml  单个
     *
     * @param map
     * @return
     */
    public static String mapToXML(Map map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        mapToXML2(map, sb);
        sb.append("</xml>");
        try {
            return sb.toString();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * map 拼接 xml  多个
     *
     * @param map
     * @param sb
     */
    private static void mapToXML2(Map map, StringBuffer sb) {
        Set set = map.keySet();
        for (Iterator it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (null == value)
                value = "";
            if (value.getClass().getName().equals("java.util.ArrayList")) {
                ArrayList list = (ArrayList) map.get(key);
                sb.append("<" + key + ">");
                for (int i = 0; i < list.size(); i++) {
                    HashMap hm = (HashMap) list.get(i);
                    mapToXML2(hm, sb);
                }
                sb.append("</" + key + ">");

            } else {
                if (value instanceof HashMap) {
                    sb.append("<" + key + ">");
                    mapToXML2((HashMap) value, sb);
                    sb.append("</" + key + ">");
                } else {
                    sb.append("<" + key + "><![CDATA[" + value + "]]></" + key + ">");
                }

            }

        }
    }

    /**
     * 回复文本消息
     *
     * @param requestMap
     * @param content
     * @return
     */
    public static String sendTextMsg(Map<String, String> requestMap, String content) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ToUserName", requestMap.get(WeChatContant.FromUserName));
        map.put("FromUserName", requestMap.get(WeChatContant.ToUserName));
        map.put("MsgType", WeChatContant.MESSAGE_TYPE_TEXT);
        map.put("CreateTime", new Date().getTime());
        map.put("Content", content);
        return mapToXML(map);
    }

    /**
     * 回复图片消息
     *
     * @param requestMap
     * @param mediaId
     * @return
     */
    public static String sendImageMsg(Map<String, String> requestMap, String mediaId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ToUserName", requestMap.get(WeChatContant.FromUserName));
        map.put("FromUserName", requestMap.get(WeChatContant.ToUserName));
        map.put("MsgType", WeChatContant.MESSAGE_TYPE_IMAGE);
        map.put("CreateTime", new Date().getTime());
        List<Map<String, Object>> Image = new ArrayList<Map<String, Object>>();
        Map<String, Object> itemContent = new HashMap<String, Object>();
        itemContent.put("MediaId", mediaId);
        Image.add(itemContent);
        map.put("Image", Image);
        return mapToXML(map);
    }

    /**
     * 回复图文消息
     * 图文消息个数；当用户发送文本、图片、视频、图文、地理位置这五种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
     *
     * @param requestMap
     * @param items
     * @return
     */
    public static String sendArticleMsg(Map<String, String> requestMap, List<Article> items) {
        if (items == null || items.size() < 1) {
            return "";
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ToUserName", requestMap.get(WeChatContant.FromUserName));
        map.put("FromUserName", requestMap.get(WeChatContant.ToUserName));
        map.put("MsgType", WeChatContant.MESSAGE_TYPE_NEWS);
        map.put("CreateTime", new Date().getTime());
        List<Map<String, Object>> Articles = new ArrayList<Map<String, Object>>();
        for (Article itembean : items) {
            Map<String, Object> item = new HashMap<String, Object>();
            Map<String, Object> itemContent = new HashMap<String, Object>();
            itemContent.put("Title", itembean.getTitle());
            itemContent.put("Description", itembean.getDescription());
            itemContent.put("PicUrl", itembean.getPicUrl());
            itemContent.put("Url", itembean.getUrl());
            item.put("item", itemContent);
            Articles.add(item);
        }
        map.put("Articles", Articles);
        map.put("ArticleCount", Articles.size());
        return mapToXML(map);
    }
}
