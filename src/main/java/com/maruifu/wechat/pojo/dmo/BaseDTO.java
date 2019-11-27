package com.maruifu.wechat.pojo.dmo;

import com.alibaba.fastjson.JSON;

/**
 * @Description: $
 * @Author: XiaoMage
 * @CreateDate: 2019/11/27$ 12:54$
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/11/27$ 12:54$
 * @UpdateRemark: 更新内容
 * @Version: 1.0
 */
public class BaseDTO {


    public BaseDTO() {
    }

    public String toString() {
        String result = "";

        try {
            result = JSON.toJSONString(this);
            return result.length() > 10000 ? result.substring(0, 9999) : result;
        } catch (Throwable var3) {
            return "FAIL_FORMAT_JSON";
        }
    }
}
