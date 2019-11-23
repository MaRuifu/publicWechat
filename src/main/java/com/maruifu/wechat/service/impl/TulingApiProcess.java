package com.maruifu.wechat.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maruifu.wechat.util.HTTPClientUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Description: $
 * @Author: XiaoMage
 * @CreateDate: 2019/11/23$ 17:38$
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/23$ 17:38$
 * @UpdateRemark: 更新内容
 * @Version: 1.0
 */



/**
 * 调用图灵机器人api接口，获取智能回复内容
 * @author pamchen-1
 *
 */
public class TulingApiProcess {
    static Logger logger = LoggerFactory.getLogger(TulingApiProcess.class);

    /**
     * 调用图灵机器人api接口，获取智能回复内容，解析获取自己所需结果
     * @param content
     * @return
     */
    public String getTulingResult(String content){



        String postUrl = "http://www.tuling123.com/openapi/api";

        JSONObject data = new JSONObject();
        data.put("key", "8b6b49c538df4f9d918057224b735dc7");
        data.put("info", content);

        logger.info("消息发送开始  {} ----》 {}", postUrl, data.toJSONString());
        String post = null;
        try {
            post = HTTPClientUtils.sendPostJsonStr(postUrl, data.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("http 请求异常 : {}", e);
        }
        logger.info("消息发送结束   {}", post);
        JSONObject result = JSON.parseObject(post);


        if(null==result){
            return "对不起，你说的话真是太高深了……";
        }





        int code = result.getIntValue("code");
        String text = result.getString("text");
        try {
            if (code == 100000) {
                logger.info("消息发送成功 code:{} errtextmsg:{}", code, text);
            } else {
                return "对不起，你说的话真是太高深了……";
            }

        } catch (Exception e) {
            return "对不起，你说的话真是太高深了……";
        }
        return text;
    }
}