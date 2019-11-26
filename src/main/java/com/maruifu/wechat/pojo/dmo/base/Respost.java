package com.maruifu.wechat.pojo.dmo.base;


public class Respost {

	/**
	 * 3. 响应参数 参数名称 是否必选 数据类型 描述 ret 是 int 返回码； 0表示成功，非0表示出错 msg 是 string
	 * 返回信息；ret非0时表示出错时错误原因 data 是 object 返回数据；ret为0时有意义 session 是 string
	 * UTF-8编码，非空且长度上限32字节 answer 是 string UTF-8编码，非空
	 */
	private String ret;
	private String msg;
	private DataRepost data;

	public Respost() {
		super();
	}

	public Respost(String ret, String msg, DataRepost data) {
		super();
		this.ret = ret;
		this.msg = msg;
		this.data = data;
	}

	public String getSession() {
		if (data == null) {
			return null;
		}
		return data.getSession();
	}

	public void setSession(String Session) {
		 data.setSession(Session);
	}
	
	
	public String getAnswer() {
		if (data == null) {
			return null;
		}
		return data.getAnswer();
	}

	public void setAnswer(String Answer) {
		 data.setAnswer(Answer);
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DataRepost getData() {
		return data;
	}

	public void setData(DataRepost data) {
		this.data = data;
	}

}
