package com.maruifu.wechat.service;

import com.maruifu.wechat.pojo.dmo.CustomLibrary;

import java.util.List;

public interface CustomLibraryService {
    CustomLibrary selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(CustomLibrary record);

    int insert(CustomLibrary record);
}