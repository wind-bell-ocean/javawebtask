package cn.arts.cyyarts.domain;

public class Transaction{
	private int transactionid;
	private int userid;
	private int artsid;
	private int orderid;
	
	public int getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
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
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
	@Override
	public String toString() {
		return "Transaction [id="+transactionid+", userid="+
	userid+", artsid="+artsid+", orderid="+orderid+"]";
	}
}