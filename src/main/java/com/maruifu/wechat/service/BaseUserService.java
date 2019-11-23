package com.maruifu.wechat.service;

import com.maruifu.wechat.pojo.dmo.BaseUser;

import java.util.List;

public interface BaseUserService {
    BaseUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(BaseUser record);

    int insert(BaseUser record);
}