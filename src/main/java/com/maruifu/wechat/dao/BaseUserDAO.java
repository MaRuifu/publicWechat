/* MaRuifu */
package com.maruifu.wechat.dao;

import com.maruifu.wechat.pojo.dmo.BaseUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * @author MaRuifu
 * @author maruifu
 */
@Repository("baseUserDAO")
public interface BaseUserDAO {
    /** 
     * 根据主键删除数据库的记录
     * @param id
     */
    @Delete({
    "delete from base_user",
    "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /** 
     * 新写入数据库记录
     * @param record
     */
    @Insert({
    "insert into base_user (name, passwd, ",
    "org_id, creater, ",
    "create_time, updater, ",
    "update_time, status_code)",
    "values (#{name,jdbcType=VARCHAR}, #{passwd,jdbcType=VARCHAR}, ",
    "#{orgId,jdbcType=INTEGER}, #{creater,jdbcType=VARCHAR}, ",
    "#{createTime,jdbcType=TIMESTAMP}, #{updater,jdbcType=VARCHAR}, ",
    "#{updateTime,jdbcType=TIMESTAMP}, #{statusCode,jdbcType=VARCHAR})"
    })
    int insert(BaseUser record);

    /** 
     * 动态字段,写入数据库记录
     * @param record
     */
    @InsertProvider(type=BaseUserSqlProvider.class, method="insertSelective")
    int insertSelective(BaseUser record);

    /** 
     * 根据指定主键获取一条数据库记录
     * @param id
     */
    @Select({
    "select",
    "id, name, passwd, org_id, creater, create_time, updater, update_time, status_code",
    "from base_user",
    "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
    @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
    @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
    @Result(column="passwd", property="passwd", jdbcType=JdbcType.VARCHAR),
    @Result(column="org_id", property="orgId", jdbcType=JdbcType.INTEGER),
    @Result(column="creater", property="creater", jdbcType=JdbcType.VARCHAR),
    @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
    @Result(column="updater", property="updater", jdbcType=JdbcType.VARCHAR),
    @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
    @Result(column="status_code", property="statusCode", jdbcType=JdbcType.VARCHAR)
    })
    BaseUser selectByPrimaryKey(Integer id);

    /** 
     * 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     */
    @UpdateProvider(type=BaseUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BaseUser record);

    /** 
     * 根据主键来更新符合条件的数据库记录
     * @param record
     */
    @Update({
    "update base_user",
    "set name = #{name,jdbcType=VARCHAR},",
    "passwd = #{passwd,jdbcType=VARCHAR},",
    "org_id = #{orgId,jdbcType=INTEGER},",
    "creater = #{creater,jdbcType=VARCHAR},",
    "create_time = #{createTime,jdbcType=TIMESTAMP},",
    "updater = #{updater,jdbcType=VARCHAR},",
    "update_time = #{updateTime,jdbcType=TIMESTAMP},",
    "status_code = #{statusCode,jdbcType=VARCHAR}",
    "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(BaseUser record);

    class BaseUserSqlProvider {

        /** 
         * 动态字段,写入数据库记录
         * @param record
         */
        public String insertSelective(BaseUser record) {
            SQL sql = new SQL();
            sql.INSERT_INTO("base_user");
            
            if (record.getId() != null) {
                sql.VALUES("id", "#{id,jdbcType=INTEGER}");
            }
            
            if (record.getName() != null) {
                sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
            }
            
            if (record.getPasswd() != null) {
                sql.VALUES("passwd", "#{passwd,jdbcType=VARCHAR}");
            }
            
            if (record.getOrgId() != null) {
                sql.VALUES("org_id", "#{orgId,jdbcType=INTEGER}");
            }
            
            if (record.getCreater() != null) {
                sql.VALUES("creater", "#{creater,jdbcType=VARCHAR}");
            }
            
            if (record.getCreateTime() != null) {
                sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
            }
            
            if (record.getUpdater() != null) {
                sql.VALUES("updater", "#{updater,jdbcType=VARCHAR}");
            }
            
            if (record.getUpdateTime() != null) {
                sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
            }
            
            if (record.getStatusCode() != null) {
                sql.VALUES("status_code", "#{statusCode,jdbcType=VARCHAR}");
            }
            
            return sql.toString();
        }

        /** 
         * 动态字段,根据主键来更新符合条件的数据库记录
         * @param record
         */
        public String updateByPrimaryKeySelective(BaseUser record) {
            SQL sql = new SQL();
            sql.UPDATE("base_user");
            
            if (record.getName() != null) {
                sql.SET("name = #{name,jdbcType=VARCHAR}");
            }
            
            if (record.getPasswd() != null) {
                sql.SET("passwd = #{passwd,jdbcType=VARCHAR}");
            }
            
            if (record.getOrgId() != null) {
                sql.SET("org_id = #{orgId,jdbcType=INTEGER}");
            }
            
            if (record.getCreater() != null) {
                sql.SET("creater = #{creater,jdbcType=VARCHAR}");
            }
            
            if (record.getCreateTime() != null) {
                sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
            }
            
            if (record.getUpdater() != null) {
                sql.SET("updater = #{updater,jdbcType=VARCHAR}");
            }
            
            if (record.getUpdateTime() != null) {
                sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
            }
            
            if (record.getStatusCode() != null) {
                sql.SET("status_code = #{statusCode,jdbcType=VARCHAR}");
            }
            
            sql.WHERE("id = #{id,jdbcType=INTEGER}");
            
            return sql.toString();
        }
    }}