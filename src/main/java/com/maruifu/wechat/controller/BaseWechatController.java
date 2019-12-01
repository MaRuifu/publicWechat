package com.maruifu.wechat.controller;

import com.maruifu.wechat.pojo.dmo.BaseUser;
import com.maruifu.wechat.service.BaseUserService;
import com.maruifu.wechat.service.BaseWeChatService;
import com.maruifu.wechat.util.WeChatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: $
 * @Author: XiaoMage
 * @CreateDate: 2019/11/21$ 17:56$
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/21$ 17:56$
 * @UpdateRemark: 更新内容
 * @Version: 1.0
 */
@RestController
public class BaseWechatController {

    private static final Logger logger = LoggerFactory.getLogger(BaseWechatController.class);

    @Autowired
    private BaseWeChatService baseWeChatService;


    /**
     * 处理微信服务器发来的get请求，进行签名的验证
     * <p>
     * signature 微信端发来的签名
     * timestamp 微信端发来的时间戳
     * nonce     微信端发来的随机字符串
     * echostr   微信端发来的验证字符串
     */
    @GetMapping("/wx")
    @ResponseBody
    public void checkSignature(HttpServletRequest request, HttpServletResponse response) {
        logger.info("处理微信服务器发来的get请求start");

        //设置编码，不然接收到的消息乱码
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String signature = request.getParameter("signature");//微信加密签名
            String timestamp = request.getParameter("timestamp");//时间戳
            String nonce = request.getParameter("nonce");//随机数
            String echostr = request.getParameter("echostr");//随机字符串
            //接入验证
            if(WeChatUtil.checkSignature(signature, timestamp, nonce)) {
                if (echostr != null) {
                    //验证成功返回的值
                    response.getWriter().write(echostr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("处理微信服务器发来的get请求，进行签名的验证 出错: {}", e);
        }
    }




    /**
     * 此处是处理微信服务器的消息转发的
     *
     * @param request
     * @return
     */
    @PostMapping("/wx")
    public void processRequest(HttpServletRequest request, HttpServletResponse response) {
        logger.info("处理微信服务器发来的post请求start");

        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        String result = "";
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            // 调用核心服务类接收处理请求
            result = baseWeChatService.processRequest(request);
            response.getWriter().println(result);
        } catch (Exception e) {
            logger.error("微信post 请求：{}", e);
        }
    }

}
