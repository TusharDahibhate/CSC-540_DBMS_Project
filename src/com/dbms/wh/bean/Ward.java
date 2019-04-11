package com.dbms.wh.bean;

public class Ward {
	protected int id;
	protected int staffId;
	protected int type;

	public Ward() {
		super();
	}
	
	public Ward(int staffId, int type) {
		super();		
		this.staffId = staffId;
		this.type = type;
	}

	public Ward(int id, int staffId, int type) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
