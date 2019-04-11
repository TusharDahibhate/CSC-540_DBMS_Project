package com.dbms.wh.bean;

public class Staff {

	private static final long serialVersionUID = 1L;
	private int id;
	private Integer age;
	private Integer phoneno;
	private String name;
	private String gender;
	private String jobtitle;
	private String professionaltitle;
	private String address;
	private String department;
	
	public Staff(String name, Integer age, String gender, String jobtitle, String professionaltitle, Integer phoneno, String address, String department) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.jobtitle = jobtitle;
		this.professionaltitle = professionaltitle;
		this.phoneno = phoneno;
		this.address = address;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", age=" + age + ", name=" + name + ", gender=" + gender + ", phoneno=" + phoneno
				+ ", jobtitle=" + jobtitle + ", professionaltitle=" + professionaltitle + ", address=" + address + ",department=" + department + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public Integer getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(Integer phoneno) {
		this.phoneno = phoneno;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfessionaltitle() {
		return professionaltitle;
	}

	public void setProfessionaltitle(String professionaltitle) {
		this.professionaltitle = professionaltitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
}
