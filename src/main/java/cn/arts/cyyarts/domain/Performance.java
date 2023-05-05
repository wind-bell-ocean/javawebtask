package cn.arts.cyyarts.domain;

public class Performance{
	
	private int performanceid;
	private int salemanid;
	private int artsid;
	private int orderid;
	
	public int getPerformanceid() {
		return performanceid;
	}
	public void setPerformanceid(int performanceid) {
		this.performanceid=performanceid ;
	}
	
	public int getaSlemanid() {
		return salemanid;
	}
	public void setSalemanid(int salemanid) {
		this.salemanid= salemanid;
	}
	
	public int getartsid() {
		return artsid;
	}
	public void setartsid(int artsid) {
		this.artsid= artsid;
	}
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid= orderid;
	}
	
	@Override
	public String toString() {
		return "Performance [id="+performanceid+", salemanid="+
	salemanid+", artsid="+artsid+", orderid="+orderid+"]";
	}
}