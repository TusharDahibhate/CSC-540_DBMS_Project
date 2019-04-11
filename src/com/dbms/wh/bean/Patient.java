package com.dbms.wh.bean;

import java.util.Date;

public class Patient {

	private static final long serialVersionUID = 1L;
	private int id;
	private int age;
	private String name;
	private String ssn;
	private String phoneNo;
	private String gender;
	private Date dob;
	private String address;
	
	public Patient(int id, int age, String name, String ssn, String phoneNo, String gender, Date dob, String address) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.ssn = ssn;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
	}

	public Patient(int age, String name, String ssn, String phoneNo, String gender, Date dob, String address) {
		this.age = age;
		this.name = name;
		this.ssn = ssn;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", age=" + age + ", name=" + name + ", ssn=" + ssn + ", phoneNo=" + phoneNo
				+ ", gender=" + gender + ", dob=" + dob + ", address=" + address + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhone_no(String phone_no) {
		this.phoneNo = phone_no;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
