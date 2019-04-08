package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import com.dbms.wh.bean.Patient;

public class PatientDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public PatientDAO() {

	}

	public void createPatient(Patient patient) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				java.sql.Date sqlDate = new java.sql.Date(patient.getDob().getTime());
				statement.executeUpdate("INSERT INTO patients (name, dob, gender, ssn, address, phone_no, age) VALUES"
						+ "('" + patient.getName() + "','" + sqlDate + "','" + patient.getGender() + "','"
						+ patient.getSsn() + "','" + patient.getAddress() + "','" + patient.getPhoneNo() + "',"
						+ patient.getAge() + ")");
				System.out.println("New Patient added successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void updatePatient(Patient patient) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				java.sql.Date sqlDate = new java.sql.Date(patient.getDob().getTime());
				statement.executeUpdate("UPDATE patients SET name = '" + patient.getName() + "', dob = '" + sqlDate
						+ "', gender = '" + patient.getGender() + "', ssn = '" + patient.getSsn() + "', address = '"
						+ patient.getAddress() + "', phone_no = '" + patient.getPhoneNo() + "', age = "
						+ patient.getAge() + "  WHERE id = " + patient.getId() + ";");
				System.out.println("Patient updated successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void deletePatient(int patient_id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("DELETE from patients where id = " + patient_id + ";");
				System.out.println("Patient deleted successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Throwable whatever) {
			}
		}
	}

	static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Throwable whatever) {
			}
		}
	}

	static void close(ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (Throwable whatever) {
			}
		}
	}
}
