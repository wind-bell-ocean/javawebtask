package cn.arts.cyyarts.domain;

public class Salecar{
	
	private int caritemid;
	private int userid;
	private int artsid;
	
	public int getCaritemid() {
		return caritemid;
	}
	public void setCaritem(int caritemid) {
		this.caritemid = caritemid;
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
	
	@Override
	public String toString() {
		return "Salecaritem [id="+caritemid+", userid="+
	userid+", artsid="+artsid+"]";
	}
}