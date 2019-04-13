package com.dbms.wh.bean;

public class MedicalHistory {

	private static final long serialVersionUID = 1L;

	CheckIn checkin;
	MedicalRecord record;
	Patient patient;
	Staff staff;

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public CheckIn getCheckin() {
		return checkin;
	}

	public void setCheckin(CheckIn checkin) {
		this.checkin = checkin;
	}

	public MedicalRecord getRecord() {
		return record;
	}

	public void setRecord(MedicalRecord record) {
		this.record = record;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public MedicalHistory(CheckIn checkin, MedicalRecord record, Patient patient, Staff staff) {
		super();
		this.checkin = checkin;
		this.record = record;
		this.patient = patient;
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "MedicalHistory [checkin=" + checkin + ", record=" + record + ", patient=" + patient + ", staff=" + staff
				+ "]";
	}

}
