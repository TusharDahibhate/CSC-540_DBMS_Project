package com.dbms.wh.bean;

public class Staff {

	private static final long serialVersionUID = 1L;
	private int id;
	private Integer age;
	private Integer phone_no;
	private String name;
	private String gender;
	private String job_title;
	private String professional_title;
	private String address;
	private String department;
	
	public Staff(String name, Integer age, String gender, String job_title, String professional_title, Integer phone_no, String address, String department) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.job_title = job_title;
		this.professional_title = professional_title;
		this.phone_no = phone_no;
		this.address = address;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", age=" + age + ", name=" + name + ", gender=" + gender + ", phone_no=" + phone_no
				+ ", job_title=" + job_title + ", professional_title=" + professional_title + ", address=" + address + ",department=" + department + "]";
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

	public String getJobTitle() {
		return job_title;
	}

	public void setJobTitle(String job_title) {
		this.job_title = job_title;
	}

	public Integer getPhoneNo() {
		return phone_no;
	}

	public void setPhoneNo(Integer phone_no) {
		this.phone_no = phone_no;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfessionalTitle() {
		return professional_title;
	}

	public void setProfessionalTitle(String professional_title) {
		this.professional_title = professional_title;
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
