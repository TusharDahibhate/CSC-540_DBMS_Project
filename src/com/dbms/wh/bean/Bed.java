package com.dbms.wh.bean;

public class Bed {
	protected int id;
	protected int ward_id;
	protected int rate;
	protected int checkin_id;

	public Bed() {
	}

	public Bed(int ward_id, int rate, int checkin_id) {
		super();
		this.ward_id = ward_id;
		this.rate = rate;
		this.checkin_id = checkin_id;
	}

	public Bed(int id, int ward_id, int rate, int checkin_id) {
		super();
		this.id = id;
		this.ward_id = ward_id;
		this.rate = rate;
		this.checkin_id = checkin_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWard_id() {
		return ward_id;
	}

	public void setWard_id(int ward_id) {
		this.ward_id = ward_id;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getCheckin_id() {
		return checkin_id;
	}

	public void setCheckin_id(int checkin_id) {
		this.checkin_id = checkin_id;
	}
	
	

	}
