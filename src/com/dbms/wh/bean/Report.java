package com.dbms.wh.bean;

import java.util.Date;

public class Report {
	private int patient_id;
	private int checkin_id;
	private Date start_date;
	private Date end_date;
	private int rate;
	private String diagnosis;
	private String med_name;
	private int price;
	private int quantity;

	public Report() {

	}

	public Report(int patient_id, int checkin_id, Date start_date, Date end_date, int rate) {
		super();
		this.patient_id = patient_id;
		this.checkin_id = checkin_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.rate = rate;
	}

	public Report(int patient_id, int checkin_id, Date start_date, Date end_date, String diagnosis, String med_name,
			int price, int quantity) {
		super();
		this.patient_id = patient_id;
		this.checkin_id = checkin_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.setDiagnosis(diagnosis);
		this.setMed_name(med_name);
		this.setPrice(price);
		this.setQuantity(quantity);
	}

	public int getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}

	public int getCheckin_id() {
		return checkin_id;
	}

	public void setCheckin_id(int checkin_id) {
		this.checkin_id = checkin_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getMed_name() {
		return med_name;
	}

	public void setMed_name(String med_name) {
		this.med_name = med_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
