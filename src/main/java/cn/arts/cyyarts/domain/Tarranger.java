package cn.arts.cyyarts.domain;

import java.io.Serializable;

public class Tarranger implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int arrangerid;  //管理员ID
	private String nickname;  //管理员昵称
	private String password;  // 密码
	
	public int getArrangerid() {
		return arrangerid;
	}
	public void setArrangerid(int arrangerid) {
		this.arrangerid = arrangerid;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Arranger [salemanid="+arrangerid+", nickname="+
	nickname+", password="+password+"]";
	}
}