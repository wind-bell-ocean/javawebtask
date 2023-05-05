package cn.arts.cyyarts.domain;

import java.io.Serializable;

public class Arts implements Serializable {
	
	private  static final long serialVersionUID = 1L;
	private String artsid;  //商品ID；
	private String artsname;  //商品名称,由于函数需要，具有唯一性；
	private String category;  //商品分类
	private String description;  //商品介绍
	private String imgurl;  //商品图片URL
	private int price;  //商品价格
	private int storenum;  //商品库存
	private int salemanid;  //商品所属负责销售人员ID
	
	public String getArtsid() {
		return artsid;
	}
	public void setArtsid(String artsid) {
		this.artsid = artsid;
	}
	
	public String getArtsname() {
		return artsname;
	}
	public void setArtsname(String artsname) {
		this.artsname = artsname;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getStorenum() {
		return storenum;
	}
	public void setStorenum(int storenum) {
		this.storenum = storenum;
	}
	
	public int getSalemanid() {
		return salemanid;
	}
	public void setSalemanid(int salemanid) {
		this.salemanid = salemanid;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artsname == null) ? 0 : artsname.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arts other = (Arts) obj;
		if(artsid == null) {
			if(other.artsid != null)
				return false;
		} else if (!artsid.equals(other.artsid))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Arts [artsid="+artsid+", artsname="+artsname+
				", artsgroup="+category+", description='"
				+description+"', imgurl="+imgurl+", price="
				+price+", storenum="+storenum+", salemanid="
				+salemanid+"]";
	}
}