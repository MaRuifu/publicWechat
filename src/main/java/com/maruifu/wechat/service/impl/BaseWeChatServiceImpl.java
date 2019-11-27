package com.maruifu.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maruifu.wechat.service.BaseWeChatService;
import com.maruifu.wechat.util.MessageUtil;
import com.maruifu.wechat.util.WeChatContant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import static java.util.stream.Collectors.toList;

@Service
public class BaseWeChatServiceImpl implements BaseWeChatService {

    static Logger logger = LoggerFactory.getLogger(BaseWeChatService.class);

    @Override
    public String processRequest(HttpServletRequest request) {
 
        // xml格式的消息数据
        String respXml = "";
        // 默认返回的文本消息内容
        String respContent;
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            logger.info("requestMap>>> {}", requestMap);
            // 消息类型
            String msgType = requestMap.get(WeChatContant.MsgType);
            logger.info("msgType {}", msgType);
            String mes = null;
            // 文本消息
            if (msgType.equals(WeChatContant.MESSAGE_TYPE_TEXT)) {


//                respXml=  MessageUtil.sendTextMsg(requestMap,new TulingApiProcess().getTulingResult(requestMap.get(WeChatContant.Content)));
                respXml=  MessageUtil.sendTextMsg(requestMap,new TencentAiApiProcess().getTencentAiResult(requestMap.get(WeChatContant.Content)));

            }
            // 图片消息
            else if (msgType.equals(WeChatContant.MESSAGE_TYPE_IMAGE)) {
//                respXml=  MessageUtil.sendTextMsg(requestMap,requestMap.get(WeChatContant.MESSAGE_TYPE_IMAGE));

                respXml="暂不支持此功能,敬请期待!";
            }
            // 语音消息
            else if (msgType.equals(WeChatContant.MESSAGE_TYPE_VOICE)) {
                respXml="暂不支持此功能,敬请期待!";

            }
            // 视频消息
            else if (msgType.equals(WeChatContant.MESSAGE_TYPE_VIDEO)) {
                respXml="暂不支持此功能,敬请期待!";

            }
            // 地理位置消息
            else if (msgType.equals(WeChatContant.MESSAGE_TYPE_LOCATION)) {
                respXml="暂不支持此功能,敬请期待!";

            }
            // 链接消息
            else if (msgType.equals(WeChatContant.MESSAGE_TYPE_LINK)) {
                respXml="暂不支持此功能,敬请期待!";

            }
            // 事件推送
            else if (msgType.equals(WeChatContant.MESSAGE_TYPE_EVENT)) {
                mes = "";
                // 事件类型
                String eventType = (String) requestMap.get(WeChatContant.Event);
                logger.info("eventType {}", eventType);
                //事件的key 类型
                String eventKey = (String) requestMap.get(WeChatContant.EventKey);
                logger.info("eventKey {}", eventKey);
                // 关注
                if (eventType.equals(WeChatContant.EVENT_TYPE_SUBSCRIBE)) {
                    logger.info("微信关注发送消息");
                    //避免超时时微信重新请求
                    respXml = "";
                }
                // 取消关注
                else if (eventType.equals(WeChatContant.EVENT_TYPE_UNSUBSCRIBE)) {


                }
                // 扫描带参数二维码
                else if (eventType.equalsIgnoreCase(WeChatContant.EVENT_TYPE_SCAN)) {
                    //超时5s,进行异步发送客服消息
                    //避免超时时微信重新请求
                    respXml = "";
                }
                // 上报地理位置
                else if (eventType.equalsIgnoreCase(WeChatContant.EVENT_TYPE_LOCATION)) {
                    //  处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(WeChatContant.EVENT_TYPE_CLICK)) {
                    // 自定义菜单事件
                }
            }
            return respXml;
        } catch (Exception e) {
            logger.error("Exception {}", e);
        }
        return "";
    }

}
