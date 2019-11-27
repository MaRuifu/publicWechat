package com.maruifu.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.maruifu.wechat.dao.CustomLibraryDAO;
import com.maruifu.wechat.pojo.dmo.base.Respost;
import com.maruifu.wechat.util.HTTPClientUtils;
import com.maruifu.wechat.util.HttpUtil;
import com.maruifu.wechat.util.Sign;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
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
public class TencentAiApiProcess {
    static Logger logger = LoggerFactory.getLogger(TencentAiApiProcess.class);



    @Autowired
    private CustomLibraryDAO customLibraryDAO;

    public static String url = "https://api.ai.qq.com/fcgi-bin/nlp/nlp_textchat";
    /**
     * 调用腾讯基础闲聊api接口，获取智能回复内容，解析获取自己所需结果
     * @param content
     * @return
     */
    public String getTencentAiResult(String content){


       String  sentenceValus =  customLibraryDAO.selectBySentenceKey(content);
       if(StringUtils.isNotBlank(sentenceValus)){
           logger.info("sentenceValus!",sentenceValus);
           return  sentenceValus;
       }

        String resp = null;
        try {
            resp = HttpUtil.get(url, Sign.getSignature(Sign.getKeybyvalue(content), "2RtEHaOZgmKHapvW"));
        } catch (Exception e) {
            logger.error("调用腾讯基础闲聊api接口失败!",e);
            return "对不起，你说的话真是太高深了……";
        }
        JSONObject jsonObject = JSONObject.parseObject(resp);
        Respost respost =  jsonObject.toJavaObject(jsonObject, Respost.class);
        logger.info("ResponseMap>>> {}",respost);

        return respost.getAnswer();
    }
}