package com.maruifu.wechat.service.impl;


import com.maruifu.wechat.dao.CustomLibraryDAO;
import com.maruifu.wechat.pojo.dmo.CustomLibrary;
import com.maruifu.wechat.service.CustomLibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomLibraryServiceImpl implements CustomLibraryService {
    private static final Logger logger = LoggerFactory.getLogger(CustomLibraryServiceImpl.class);

    @Autowired
    private CustomLibraryDAO customLibraryDAO;

    @Override
    public CustomLibrary selectByPrimaryKey(Integer id) {
        return this.customLibraryDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(CustomLibrary record) {
        return this.customLibraryDAO.updateByPrimaryKey(record);
    }

    @Override
    public int insert(CustomLibrary record) {
        return this.customLibraryDAO.insert(record);
    }
}