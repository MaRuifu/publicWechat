package com.maruifu.wechat.controller;

import com.maruifu.wechat.pojo.dmo.BaseUser;
import com.maruifu.wechat.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("baseUserServiceImpl")
    private BaseUserService baseUserService;

    @RequestMapping("/user/queryById")
    @ResponseBody
    public BaseUser show(Integer id) {
        return baseUserService.selectByPrimaryKey(id);
    }


}
