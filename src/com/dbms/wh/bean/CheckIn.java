package com.dbms.wh.bean;

import java.util.Date;

public class CheckIn {

	private static final long serialVersionUID = 1L;
	private int id;
	private int patient_id;
	private int staff_id;
	private Date start_date;
	private Date end_date;

	public CheckIn(int patient_id, int staff_id, Date start_date, Date end_date) {
		this.patient_id = patient_id;
		this.staff_id = staff_id;
		this.start_date = start_date;
		this.end_date = end_date;
	}

	@Override
	public String toString() {
		return "Checkin [id=" + id + ", patient_id=" + patient_id + ", staff_id=" + staff_id + ", start_date="
				+ start_date + ", end_date=" + end_date + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatientId() {
		return patient_id;
	}

	public void setPatientId(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getStaffId() {
		return staff_id;
	}

	public void setStaffId(int staff_id) {
		this.staff_id = staff_id;
	}

	public Date getStartDate() {
		return start_date;
	}

	public void setStartDate(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEndDate() {
		return end_date;
	}

	public void setEndDate(Date end_date) {
		this.end_date = end_date;
	}
}
