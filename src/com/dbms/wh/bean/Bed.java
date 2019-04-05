package com.dbms.wh.bean;

public class Bed {
	protected int id;
	protected int wardId;
	protected int rate;
	protected int checkinId;

	public Bed() {
	}

	public Bed(int ward_id, int rate, int checkin_id) {
		super();
		this.wardId = ward_id;
		this.rate = rate;
		this.checkinId = checkin_id;
	}

	public Bed(int id, int ward_id, int rate, int checkin_id) {
		super();
		this.id = id;
		this.wardId = ward_id;
		this.rate = rate;
		this.checkinId = checkin_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWardId() {
		return wardId;
	}

	public void setWardId(int ward_id) {
		this.wardId = ward_id;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getCheckin_id() {
		return checkinId;
	}

	public void setCheckin_id(int checkin_id) {
		this.checkinId = checkin_id;
	}
	
	

	}
