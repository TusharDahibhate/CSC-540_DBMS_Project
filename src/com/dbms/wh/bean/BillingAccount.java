package com.dbms.wh.bean;

public class BillingAccount {
	private int id;
	private int staff_id;
	private int checkin_id;
	private int paid_by_person;
	private int paid_by_insurance;
	private String payment_info;
	private String payee_ssn;
	private String billing_address;
	private int total_charge;
	
	public BillingAccount(int staff_id, int checkin_id, int paid_by_person, int paid_by_insurance, String payment_info,
			String payee_ssn, String billing_address, int total_charge) {
		super();
		this.staff_id = staff_id;
		this.checkin_id = checkin_id;
		this.paid_by_person = paid_by_person;
		this.paid_by_insurance = paid_by_insurance;
		this.payment_info = payment_info;
		this.payee_ssn = payee_ssn;
		this.billing_address = billing_address;
		this.total_charge = total_charge;
	}
	
	public BillingAccount(int id, int staff_id, int checkin_id, int paid_by_person, int paid_by_insurance,
			String payment_info, String payee_ssn, String billing_address, int total_charge) {
		super();
		this.id = id;
		this.staff_id = staff_id;
		this.checkin_id = checkin_id;
		this.paid_by_person = paid_by_person;
		this.paid_by_insurance = paid_by_insurance;
		this.payment_info = payment_info;
		this.payee_ssn = payee_ssn;
		this.billing_address = billing_address;
		this.total_charge = total_charge;
	}

	@Override
	public String toString() {
		return "BillingAccount [id=" + id + ", staff_id=" + staff_id + ", checkin_id=" + checkin_id
				+ ", paid_by_person=" + paid_by_person + ", paid_by_insurance=" + paid_by_insurance + ", payment_info="
				+ payment_info + ", payee_ssn=" + payee_ssn + ", billing_address=" + billing_address + ", total_charge="
				+ total_charge + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}

	public int getCheckin_id() {
		return checkin_id;
	}

	public void setCheckin_id(int checkin_id) {
		this.checkin_id = checkin_id;
	}

	public int getPaid_by_person() {
		return paid_by_person;
	}

	public void setPaid_by_person(int paid_by_person) {
		this.paid_by_person = paid_by_person;
	}

	public int getPaid_by_insurance() {
		return paid_by_insurance;
	}

	public void setPaid_by_insurance(int paid_by_insurance) {
		this.paid_by_insurance = paid_by_insurance;
	}

	public String getPayment_info() {
		return payment_info;
	}

	public void setPayment_info(String payment_info) {
		this.payment_info = payment_info;
	}

	public String getPayee_ssn() {
		return payee_ssn;
	}

	public void setPayee_ssn(String payee_ssn) {
		this.payee_ssn = payee_ssn;
	}

	public String getBilling_address() {
		return billing_address;
	}

	public void setBilling_address(String billing_address) {
		this.billing_address = billing_address;
	}

	public int getTotal_charge() {
		return total_charge;
	}

	public void setTotal_charge(int total_charge) {
		this.total_charge = total_charge;
	}
	
}
