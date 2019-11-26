package com.maruifu.wechat.pojo.dmo.base;

public class DataRepost {
/**
 * data	是	object	返回数据；ret为0时有意义
session	是	string	UTF-8编码，非空且长度上限32字节
answer	是	string	UTF-8编码，非空
 */
	private String session;
	private String answer;
	
	public DataRepost() {
		super();
	}
	public DataRepost(String session, String answer) {
		super();
		this.session = session;
		this.answer = answer;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
