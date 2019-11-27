/* MaRuifu */
package com.maruifu.wechat.dao;

import com.maruifu.wechat.pojo.dmo.CustomLibrary;
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
@Repository("customLibraryDAO")
public interface CustomLibraryDAO {
    /** 
     * 根据主键删除数据库的记录
     * @param id
     */
    @Delete({
    "delete from custom_library",
    "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /** 
     * 新写入数据库记录
     * @param record
     */
    @Insert({
    "insert into custom_library (sentence_key, sentence_valus)",
    "values (#{sentenceKey,jdbcType=VARCHAR}, #{sentenceValus,jdbcType=INTEGER})"
    })
    int insert(CustomLibrary record);

    /** 
     * 动态字段,写入数据库记录
     * @param record
     */
    @InsertProvider(type=CustomLibrarySqlProvider.class, method="insertSelective")
    int insertSelective(CustomLibrary record);

    /** 
     * 根据指定主键获取一条数据库记录
     * @param id
     */
    @Select({
    "select",
    "id, sentence_key, sentence_valus",
    "from custom_library",
    "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
    @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
    @Result(column="sentence_key", property="sentenceKey", jdbcType=JdbcType.VARCHAR),
    @Result(column="sentence_valus", property="sentenceValus", jdbcType=JdbcType.INTEGER)
    })
    CustomLibrary selectByPrimaryKey(Integer id);


    @Select({
    "select sentence_valus from custom_library",
    "where sentence_key = #{sentenceKey,jdbcType=VARCHAR}  order by rand() limit 1"
    })
    @Results({
    @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
    @Result(column="sentence_key", property="sentenceKey", jdbcType=JdbcType.VARCHAR),
    @Result(column="sentence_valus", property="sentenceValus", jdbcType=JdbcType.INTEGER)
    })
    String selectBySentenceKey(String sentenceKey);

    /** 
     * 动态字段,根据主键来更新符合条件的数据库记录
     * @param record
     */
    @UpdateProvider(type=CustomLibrarySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CustomLibrary record);

    /** 
     * 根据主键来更新符合条件的数据库记录
     * @param record
     */
    @Update({
    "update custom_library",
    "set sentence_key = #{sentenceKey,jdbcType=VARCHAR},",
    "sentence_valus = #{sentenceValus,jdbcType=INTEGER}",
    "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CustomLibrary record);

    class CustomLibrarySqlProvider {

        /** 
         * 动态字段,写入数据库记录
         * @param record
         */
        public String insertSelective(CustomLibrary record) {
            SQL sql = new SQL();
            sql.INSERT_INTO("custom_library");
            
            if (record.getId() != null) {
                sql.VALUES("id", "#{id,jdbcType=INTEGER}");
            }
            
            if (record.getSentenceKey() != null) {
                sql.VALUES("sentence_key", "#{sentenceKey,jdbcType=VARCHAR}");
            }
            
            if (record.getSentenceValus() != null) {
                sql.VALUES("sentence_valus", "#{sentenceValus,jdbcType=INTEGER}");
            }
            
            return sql.toString();
        }

        /** 
         * 动态字段,根据主键来更新符合条件的数据库记录
         * @param record
         */
        public String updateByPrimaryKeySelective(CustomLibrary record) {
            SQL sql = new SQL();
            sql.UPDATE("custom_library");
            
            if (record.getSentenceKey() != null) {
                sql.SET("sentence_key = #{sentenceKey,jdbcType=VARCHAR}");
            }
            
            if (record.getSentenceValus() != null) {
                sql.SET("sentence_valus = #{sentenceValus,jdbcType=INTEGER}");
            }
            
            sql.WHERE("id = #{id,jdbcType=INTEGER}");
            
            return sql.toString();
        }
    }}