package com.dbms.wh.bean;

public class TestReport {
	private int id;
	private int checkin_id;
	private int test_id;
	private String result;
	private String patient_name;
	private String test_name;
	private float price;

	public TestReport(int id, int checkin_id, int test_id, String result) {
		this.id = id;
		this.checkin_id = checkin_id;
		this.test_id = test_id;
		this.result = result;
	}

	public TestReport(int checkin_id, int test_id, String result) {
		this.checkin_id = checkin_id;
		this.test_id = test_id;
		this.result = result;
	}

	public TestReport(int id, int checkin_id, String patient_name, int test_id, String test_name, String result) {
		this.id = id;
		this.patient_name = patient_name;
		this.test_name = test_name;
		this.checkin_id = checkin_id;
		this.test_id = test_id;
		this.result = result;
	}
	
	public TestReport(int id, int checkin_id, String patient_name, int test_id, String test_name, float price, String result) {
		this.id = id;
		this.patient_name = patient_name;
		this.test_name = test_name;
		this.checkin_id = checkin_id;
		this.test_id = test_id;
		this.result = result;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCheckin_id() {
		return checkin_id;
	}

	public void setCheckin_id(int checkin_id) {
		this.checkin_id = checkin_id;
	}

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getTest_name() {
		return test_name;
	}

	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
