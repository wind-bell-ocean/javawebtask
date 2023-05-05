package cn.arts.cyyarts.domain;

import java.io.Serializable;
import java.util.Date;

public class Logrecord implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int logid;
	private int userid;
	private String logip;
	private Date login_time;
	private Date logout_time;
	
	public int getLogid() {
		return logid;
	}
	public void setLogid(int logid) {
		this.logid = logid;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getLogip() {
		return logip;
	}
	public void setLogip(String logip) {
		this.logip = logip;
	}
	
	public Date getLogintime() {
		return login_time;
	}
	public void setLogintime(Date login_time) {
		this.login_time = login_time;
	}
	
	public Date getLogouttime() {
		return logout_time;
	}
	public void setLogouttime(Date logout_time) {
		this.logout_time = logout_time;
	}
	
	@Override
	public String toString() {
		return "Log [id="+logid+", userid="+userid+", logip="
	+logip+", login_time="+login_time+", logout_time="
				+logout_time+"]";
	}
	
}