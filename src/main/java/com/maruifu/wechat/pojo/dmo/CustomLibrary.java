/* MaRuifu */
package com.maruifu.wechat.pojo.dmo;

/**
 * @author maruifu
 */
public class CustomLibrary extends BaseDTO {
    /** 
     * ID
     */
    private Integer id;

    /** 
     * 问
     */
    private String sentenceKey;

    /** 
     * 答
     */
    private Integer sentenceValus;

    /** 
     * 获取 ID custom_library.id
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /** 
     * 设置 ID custom_library.id
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 
     * 获取 问 custom_library.sentence_key
     * @return 问
     */
    public String getSentenceKey() {
        return sentenceKey;
    }

    /** 
     * 设置 问 custom_library.sentence_key
     * @param sentenceKey 问
     */
    public void setSentenceKey(String sentenceKey) {
        this.sentenceKey = sentenceKey == null ? null : sentenceKey.trim();
    }

    /** 
     * 获取 答 custom_library.sentence_valus
     * @return 答
     */
    public Integer getSentenceValus() {
        return sentenceValus;
    }

    /** 
     * 设置 答 custom_library.sentence_valus
     * @param sentenceValus 答
     */
    public void setSentenceValus(Integer sentenceValus) {
        this.sentenceValus = sentenceValus;
    }
}