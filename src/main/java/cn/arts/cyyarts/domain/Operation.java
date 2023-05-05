package cn.arts.cyyarts.domain;

import java.util.Date;

public class Operation{
	
	private int operateid;
	private int operaterid;
	private String operation;
	private String operaterip;
	private Date operationtime;
	
	public int getOperateid() {
		return operateid;
	}
	public void setOperateid(int operateid) {
		this.operateid = operateid;
	}
	
	public int getOperaterid() {
		return operaterid;
	}
	public void setOperaterid(int operaterid) {
		this.operaterid = operaterid;
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getOperaterip() {
		return operaterip;
	}
	public void setOperaterip(String operaterip) {
		this.operaterip = operaterip;
	}
	
	@Override
	public String toString() {
		return "Operation [id="+operateid+", operaterid="+
	operaterid+", operation="+operation+", operaterip="+
				operaterip+", operationtime="+operationtime+"]";
	}
}