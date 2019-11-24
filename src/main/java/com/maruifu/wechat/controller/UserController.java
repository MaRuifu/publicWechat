package com.maruifu.wechat.controller;

import com.maruifu.wechat.pojo.dmo.BaseUser;
import com.maruifu.wechat.service.BaseUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    @Qualifier("baseUserServiceImpl")
    private BaseUserService baseUserService;

    @RequestMapping("/user/queryById")
    @ResponseBody
    public BaseUser show(@RequestBody  Integer id) {
        logger.info("查询用户1");
        logger.error("查询用户2");

        return baseUserService.selectByPrimaryKey(id);
    }


}
