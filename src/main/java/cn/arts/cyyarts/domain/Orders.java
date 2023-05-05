
package cn.arts.cyyarts.domain;

import java.util.Date;

public class Orders {
	
	private int orderid;
	private int userid;
	private int salemanid;
	private int orderstatus;
	private Date ordertime;
	private int salenum;
	private int totalprice;
	private String artsid;
	private String artsname;
	private String receivername;
	private String receiveraddr;
	private String receivertel;
	
	private User user;
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid=orderid;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid=userid;
	}
	
	public int getSalemanid() {
		return salemanid;
	}
	public void setSalemanid(int salemanid) {
		this.salemanid=salemanid;
	}
	
	public int getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(int orderstatus) {
		this.orderstatus=orderstatus;
	}
	
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	
	public int getSalenum() {
		return salenum;
	}
	public void setSalenum(int salenum) {
		this.salenum=salenum;
	}
	
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice= totalprice;
	}
	
	public String getArtsid() {
		return artsid;
	}
	public void setArtsid(String artsid) {
		this.artsid=artsid;
	}
	
	public String getArtsname() {
		return artsname;
	}
	public void setArtsname(String artsname) {
		this.artsname=artsname;
	}
	
	public String getReceivername() {
		return receivername;
	}
	public void setReceivername(String receivername) {
		this.receivername=receivername;
	}
	
	public String getReceiveraddr() {
		return receiveraddr;
	}
	public void setReceiveraddr(String receiveraddr) {
		this.receiveraddr=receiveraddr;
	}
	
	public String getReceivertel() {
		return receivertel;
	}
	public void setReceivertel(String receivertel) {
		this.receivertel=receivertel;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + orderid +", userid="+userid+
				", salemanid="+salemanid+", orderstatus="+
				orderstatus+", ordertime="+ordertime+
				", salenum="+salenum+", totalprice="+totalprice+
				", artsid="+artsid+", artsname="+artsname+
				", receivername="+receivername+", receiveraddr="
				+receiveraddr+", receivertel="+receivertel+"]";
	}
}