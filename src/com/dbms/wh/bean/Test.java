package com.dbms.wh.bean;

public class Test {
	private int id;
	private String name;
	private int price;
	Staff staff;

	public Test(int id, String name, int price, Staff staff) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
	public Staff getStaff() {
		return staff;
	}

}
