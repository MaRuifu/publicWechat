package com.maruifu.wechat.service.impl;

import java.util.List;

import com.maruifu.wechat.dao.BaseUserDAO;
import com.maruifu.wechat.pojo.dmo.BaseUser;
import com.maruifu.wechat.service.BaseUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service("baseUserServiceImpl")
public class BaseUserServiceImpl implements BaseUserService {
    private static final Logger logger = LoggerFactory.getLogger(BaseUserServiceImpl.class);

    @Autowired
    private BaseUserDAO baseUserDAO;

    @Override
    public BaseUser selectByPrimaryKey(Integer id) {
        return this.baseUserDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(BaseUser record) {
        return this.baseUserDAO.updateByPrimaryKey(record);
    }

    @Override
    public int insert(BaseUser record) {
        return this.baseUserDAO.insert(record);
    }
}