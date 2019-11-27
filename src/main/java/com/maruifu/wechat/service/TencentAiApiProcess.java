package com.maruifu.wechat.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface TencentAiApiProcess {

    String getTencentAiResult(String content);
}
