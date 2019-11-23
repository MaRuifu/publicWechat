/* MaRuifu */
package com.maruifu.wechat.pojo.dmo;

import java.util.Date;

/**
 * @author maruifu
 */
public class BaseUser  {
    /** 
     * 员工ID
     */
    private Integer id;

    /** 
     * 员工姓名
     */
    private String name;

    /** 
     * 加密后的密码，加盐后的md5，长度32位
     */
    private String passwd;

    /** 
     * 归属组织机构ID
     */
    private Integer orgId;

    /** 
     * 创建人
     */
    private String creater;

    /** 
     * 创建时间
     */
    private Date createTime;

    /** 
     * 更新人
     */
    private String updater;

    /** 
     * 更新时间
     */
    private Date updateTime;

    /** 
     * 用户状态  USE_FLAG_Y:启用 USE_FLAG_N:停用  默认：Y
     */
    private String statusCode;

    /** 
     * 获取 员工ID base_user.id
     * @return 员工ID
     */
    public Integer getId() {
        return id;
    }

    /** 
     * 设置 员工ID base_user.id
     * @param id 员工ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 
     * 获取 员工姓名 base_user.name
     * @return 员工姓名
     */
    public String getName() {
        return name;
    }

    /** 
     * 设置 员工姓名 base_user.name
     * @param name 员工姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** 
     * 获取 加密后的密码，加盐后的md5，长度32位 base_user.passwd
     * @return 加密后的密码，加盐后的md5，长度32位
     */
    public String getPasswd() {
        return passwd;
    }

    /** 
     * 设置 加密后的密码，加盐后的md5，长度32位 base_user.passwd
     * @param passwd 加密后的密码，加盐后的md5，长度32位
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    /** 
     * 获取 归属组织机构ID base_user.org_id
     * @return 归属组织机构ID
     */
    public Integer getOrgId() {
        return orgId;
    }

    /** 
     * 设置 归属组织机构ID base_user.org_id
     * @param orgId 归属组织机构ID
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    /** 
     * 获取 创建人 base_user.creater
     * @return 创建人
     */
    public String getCreater() {
        return creater;
    }

    /** 
     * 设置 创建人 base_user.creater
     * @param creater 创建人
     */
    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    /** 
     * 获取 创建时间 base_user.create_time
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 
     * 设置 创建时间 base_user.create_time
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     * 获取 更新人 base_user.updater
     * @return 更新人
     */
    public String getUpdater() {
        return updater;
    }

    /** 
     * 设置 更新人 base_user.updater
     * @param updater 更新人
     */
    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    /** 
     * 获取 更新时间 base_user.update_time
     * @return 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /** 
     * 设置 更新时间 base_user.update_time
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /** 
     * 获取 用户状态  USE_FLAG_Y:启用 USE_FLAG_N:停用 base_user.status_code
     * @return 用户状态  USE_FLAG_Y:启用 USE_FLAG_N:停用
     */
    public String getStatusCode() {
        return statusCode;
    }

    /** 
     * 设置 用户状态  USE_FLAG_Y:启用 USE_FLAG_N:停用 base_user.status_code
     * @param statusCode 用户状态  USE_FLAG_Y:启用 USE_FLAG_N:停用
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode == null ? null : statusCode.trim();
    }
}