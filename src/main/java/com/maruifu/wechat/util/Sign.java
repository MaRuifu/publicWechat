package com.maruifu.wechat.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author lihuate
 *
 */

public class Sign {


    /**
     * 随机字符串
     *
     * @param length x
     * @return x
     */
    public String getRandomString(int length) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 参数组合
     *@param 组合
     */
    public static Map<String, Object> getKeybyvalue(String msg){

        String app_Id = "2124791012";
        String session = "10000";
        String question = msg;
        Sign sign = new Sign();
        Map<String, Object> params = new HashMap<>();
        params.put("app_id", app_Id);
        params.put("time_stamp", new Date().getTime() / 1000);
        params.put("nonce_str", sign.getRandomString(16));
        params.put("session", session);
        params.put("question", question);

        return params;

    }
    /**
     * SIGN签名生成算法-JAVA版本 通用。默认参数都为UTF-8适用
     *
     * @param params 请求参数集，所有参数必须已转换为字符串类型
     * @return 签名
     * @throws IOException
     */
    public static Map<String, Object> getSignature(Map<String, Object> params,String CONFIG ) throws IOException {
        Map<String, Object> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();
        StringBuilder baseString = new StringBuilder();
        for (Map.Entry<String, Object> param : entrys) {
            if (param.getValue() != null && !"".equals(param.getKey().trim()) &&
                    !"sign".equals(param.getKey().trim()) && !"".equals(param.getValue())) {
                baseString.append(param.getKey().trim()).append("=")
                        .append(URLEncoder.encode(param.getValue().toString(), "UTF-8")).append("&");
            }
        }
        if (baseString.length() > 0) {
            baseString.deleteCharAt(baseString.length() - 1).append("&app_key=")
                    .append(CONFIG);
        }
        try {
            String sign = MD5Utils.getMD5String(baseString.toString());
            System.out.println("sign:" + sign.toUpperCase());
            sortedParams.put("sign", sign);

        } catch (Exception ex) {
            throw new IOException(ex);
        }
        return sortedParams;
    }


}