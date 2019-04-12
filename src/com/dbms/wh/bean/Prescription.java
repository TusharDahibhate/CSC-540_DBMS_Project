package com.dbms.wh.bean;

public class Prescription {
	protected int id;
	protected int quantity;
	protected int medicationId;
	protected int recordId;

	public Prescription() {
		super();
	}

	public Prescription(int quantity, int medicationId, int recordId) {
		super();
		this.quantity = quantity;
		this.medicationId = medicationId;
		this.recordId = recordId;
	}

	public Prescription(int id, int quantity, int medicationId, int recordId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.medicationId = medicationId;
		this.recordId = recordId;
	}

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", quantity=" + quantity + ", medicationId=" + medicationId + ", recordId="
				+ recordId + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

}
