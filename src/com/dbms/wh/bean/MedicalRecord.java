package com.dbms.wh.bean;

public class MedicalRecord {
	private int id;
	private String diagnosis;
	CheckIn checkin;
	private int checkin_id;
	Staff staff;
	private int staff_id;

	public MedicalRecord(String diagnosis, int checkin_id, int staff_id) {
		this.diagnosis = diagnosis;
		this.checkin_id = checkin_id;
		this.staff_id = staff_id;
	}

	public MedicalRecord(int id, String diagnosis, int checkin_id, int staff_id) {
		this.id = id;
		this.diagnosis = diagnosis;
		this.checkin_id = checkin_id;
		this.staff_id = staff_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public CheckIn getCheckin() {
		return checkin;
	}

	public void setCheckin(CheckIn checkin) {
		this.checkin = checkin;
	}

	public int getCheckin_id() {
		return checkin_id;
	}

	public void setCheckin_id(int checkin_id) {
		this.checkin_id = checkin_id;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

}
