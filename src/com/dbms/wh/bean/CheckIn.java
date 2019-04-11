package com.dbms.wh.bean;

import java.util.Date;

public class CheckIn {

	private static final long serialVersionUID = 1L;
	private int id;
	private int patientid;
	private int staffid;
	private Date startdate;
	private Date enddate;

	public CheckIn(int patient_id, int staff_id, Date start_date, Date end_date) {
		this.patientid = patient_id;
		this.staffid = staff_id;
		this.startdate = start_date;
		this.enddate = end_date;
	}

	@Override
	public String toString() {
		return "Checkin [id=" + id + ", patient_id=" + patientid + ", staff_id=" + staffid + ", start_date="
				+ startdate + ", end_date=" + enddate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatientid() {
		return patientid;
	}

	public void setPatientid(int patient_id) {
		this.patientid = patient_id;
	}

	public int getStaffid() {
		return staffid;
	}

	public void setStaffid(int staff_id) {
		this.staffid = staff_id;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date start_date) {
		this.startdate = start_date;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date end_date) {
		this.enddate = end_date;
	}
}
