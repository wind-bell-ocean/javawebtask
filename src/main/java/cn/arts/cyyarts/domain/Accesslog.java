package cn.arts.cyyarts.domain;

import java.io.Serializable;
import java.util.Date;

public class Accesslog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int accessid;
	private int userid;
	private int artsid;
	private String residence;
	private Date accesstime;
	
	public int getAccessid() {
		return accessid;
	}
	public void setAccessid(int accessid) {
		this.accessid = accessid;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getArtsid() {
		return artsid;
	}
	public void setArtsid(int artsid) {
		this.artsid = artsid;
	}
	
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	
	public Date getAccesstime() {
		return accesstime;
	}
	public void setAccesstime(Date accesstime) {
		this.accesstime = accesstime;
	}
	
	@Override
	public String toString() {
		return "Access [id="+accessid+", userid="+userid+
				", artsid="+artsid+", residence="+residence+
				", accesstime="+accesstime+"]";
	}
}