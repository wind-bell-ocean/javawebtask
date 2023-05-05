package cn.arts.cyyarts.domain;

import java.io.Serializable;

public class Tsaleman implements Serializable {
	private static final long serialVersionUID = 1L;
	private int salemanid;
	private String nickname;
	private String password;
	
	public int getSalemanid() {
		return salemanid;
	}
	public void getSalemanid(int salemanid) {
		this.salemanid = salemanid;
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
		return "Saleman [salemanid="+salemanid+",nickname="+nickname+",password="+password+"]";
	}
}