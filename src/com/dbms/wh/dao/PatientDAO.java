package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dbms.wh.bean.CheckIn;
import com.dbms.wh.bean.MedicalHistory;
import com.dbms.wh.bean.MedicalRecord;
import com.dbms.wh.bean.Patient;
import com.dbms.wh.bean.Staff;

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
				connection.setAutoCommit(false);
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

	public Patient selectPatient(int id) {
		Patient patient = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(
						"SELECT id, ssn, name, dob, gender, age, phone_no, address FROM patients WHERE id = " + id
								+ "");
				while (rs.next()) {
					int age = rs.getInt("age");
					String ssn = rs.getString("ssn");
					String name = rs.getString("name");
					Date dob = rs.getDate("dob");
					String gender = rs.getString("gender");
					String phoneNo = rs.getString("phone_no");
					String address = rs.getString("address");
					patient = new Patient(id, age, name, ssn, phoneNo, gender, dob, address);
				}

			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return patient;
	}

	public List<Patient> selectAllUsers() {

		List<Patient> patients = new ArrayList<>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, user, password);
			statement = connection.createStatement();
			ResultSet rs = statement
					.executeQuery("SELECT id, ssn, name, dob, gender, age, phone_no, address FROM patients");

			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String ssn = rs.getString("ssn");
				String name = rs.getString("name");
				Date dob = rs.getDate("dob");
				String gender = rs.getString("gender");
				String phoneNo = rs.getString("phone_no");
				String address = rs.getString("address");
				patients.add(new Patient(id, age, name, ssn, phoneNo, gender, dob, address));
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("From Patient DAO select Users");
		}
		return patients;
	}

	public List<MedicalHistory> getMedicalHistory(int id, Date startDate, Date endDate) {
		List<MedicalHistory> history = new ArrayList<>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, user, password);
			statement = connection.createStatement();
			java.sql.Date startD = null;
			java.sql.Date endD = null;
			
			String query = null;
			
			if(startDate == null && endDate == null) {
				query = " select * from checkins c inner join patients p on c.patient_id = p.id inner join medical_records m on m.checkin_id = c.id inner join staff s on s.id = m.staff_id where p.id = "
						+ id + ";";
			} else if(startDate != null && endDate != null) {
				startD = new java.sql.Date(startDate.getTime());
				endD = new java.sql.Date(startDate.getTime());
				query = " select * from checkins c inner join patients p on c.patient_id = p.id inner join medical_records m on m.checkin_id = c.id inner join staff s on s.id = m.staff_id where p.id = "
						+ id + " and start_date >= '" + startD + "' AND start_date <= '" + endD + "';";
			} else if(startDate != null && endDate == null) {
				startD = new java.sql.Date(startDate.getTime());				
				query = " select * from checkins c inner join patients p on c.patient_id = p.id inner join medical_records m on m.checkin_id = c.id inner join staff s on s.id = m.staff_id where p.id = "
						+ id + " and start_date >= '" + startD + "' AND start_date <= now();";
			} else if(startDate == null && endDate != null) {
				System.out.println("came here");
				endD = new java.sql.Date(endDate.getTime());				
				query = " select * from checkins c inner join patients p on c.patient_id = p.id inner join medical_records m on m.checkin_id = c.id inner join staff s on s.id = m.staff_id where p.id = "
						+ id + " AND start_date <= '"+ endD +"';";				
			}
			System.out.println(query);
			ResultSet rs = statement.executeQuery(query);
					

			while (rs.next()) {
				CheckIn checkin = new CheckIn(rs.getInt("c.id"), rs.getInt("c.patient_id"), rs.getInt("staff_id"),
						rs.getDate("start_date"), rs.getDate("end_date"));
				Patient patient = new Patient(rs.getInt("p.id"), rs.getInt("p.age"), rs.getString("p.name"),
						rs.getString("p.ssn"), rs.getString("p.phone_no"), rs.getString("p.gender"),
						rs.getDate("p.dob"), rs.getString("p.address"));
				MedicalRecord record = new MedicalRecord(rs.getInt("m.id"), rs.getString("m.diagnosis_details"),
						rs.getInt("m.checkin_id"), rs.getInt("m.staff_id"));
				Staff staff = new Staff(rs.getInt("s.id"), rs.getInt("s.age"), rs.getString("s.phone_no"),
						rs.getString("s.name"), rs.getString("s.gender"), rs.getString("s.job_title"),
						rs.getString("s.professional_title"), rs.getString("s.address"), rs.getString("s.department"));
				MedicalHistory h = new MedicalHistory(checkin, record, patient, staff);
				history.add(h);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("From Patient DAO get medical history");
		}
		return history;
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
