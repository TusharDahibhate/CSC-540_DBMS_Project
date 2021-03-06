package com.dbms.wh.bean;

public class Bed {
	protected int id;
	protected int wardId;
	protected int rate;
	protected int checkinId;

	public Bed() {
	}

	public Bed(int ward_id, int rate) {
		super();
		this.wardId = ward_id;
		this.rate = rate;		
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

	public void setWardId(int wardId) {
		this.wardId = wardId;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getCheckinId() {
		return checkinId;
	}

	public void setCheckinId(int checkinId) {
		this.checkinId = checkinId;
	}

}
