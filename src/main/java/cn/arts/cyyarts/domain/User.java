package cn.arts.cyyarts.domain;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int userid;   //用户ID编号
	private String username;  //用户昵称
	private String password;  //用户密码
	private String userrole;  //用户角色
	private String sex;  //用户性别
	private String telephone;  //联系电话
	private String email;  //电子邮箱
	private String signature;  //个性签名
	private Date enrolltime;  //注册时间
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getsex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getEnrollTime() {
		return enrolltime;
	}
	public void setEnrollTime(Date enrolltime) {
		this.enrolltime = enrolltime;
	}
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Override
	public String toString() {
		return "User [user_id="+userid+",username="+username+",password="+password+","
				+ "userrole="+userrole+",sex="+sex+",telephone="+telephone+",email="+email
				+",signature="+signature+",enrolltime="+enrolltime+"]";
	}
}