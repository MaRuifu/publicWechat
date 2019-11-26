package com.maruifu.wechat.util;
import java.util.Map;

import cn.hutool.http.HttpRequest;

public class HttpUtil {

    /**
     * 发送get请求
     *
     * @param urlString 网址
     * @return 返回内容，如果只检查状态码，正常只返回 ""，不正常返回 null
     */
    public static String get(String urlString) {
        return HttpRequest.get(urlString).execute().body();
    }

    /**
     * 发送get请求
     *
     * @param urlString 网址
     * @param paramMap post表单数据
     * @return 返回数据
     */
    public static String get(String urlString, Map<String, Object> paramMap) {
        return HttpRequest.get(urlString).form(paramMap).execute().body();
    }

    /**
     * 发送post请求
     *
     * @param urlString 网址
     * @param paramMap post表单数据
     * @return 返回数据
     */
    public static String post(String urlString, Map<String, Object> paramMap) {
        return HttpRequest.post(urlString).form(paramMap).execute().body();
    }

    /**
     * 发送post请求
     *
     * @param urlString 网址
     * @param params post表单数据
     * @return 返回数据
     */
    public static String post(String urlString, String params) {
        return HttpRequest.post(urlString).body(params).execute().body();
    }
}
