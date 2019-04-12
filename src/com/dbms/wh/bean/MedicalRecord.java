package com.dbms.wh.bean;

public class MedicalRecord {
	private int id;
	private String diagnosis;
	CheckIn checkin;
	private int checkin_id;
	Staff staff;
	private int staff_id;
	private String patient_name;
	private String doctor_name;

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

	public MedicalRecord(int id, String diagnosis, int checkin_id, String patient_name, int staff_id,
			String doctor_name) {
		this.id = id;
		this.diagnosis = diagnosis;
		this.checkin_id = checkin_id;
		this.staff_id = staff_id;
		this.patient_name = patient_name;
		this.doctor_name = doctor_name;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
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
