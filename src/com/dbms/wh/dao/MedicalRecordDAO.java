package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dbms.wh.bean.MedicalRecord;

public class MedicalRecordDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public MedicalRecordDAO() {

	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void createMedicalRecord(MedicalRecord record) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO medical_records (diagnosis_details, checkin_id, staff_id) VALUES"
						+ "('" + record.getDiagnosis() + "'," + record.getCheckin_id() + "," + record.getStaff_id()
						+ ")");
				System.out.println("New Medical Record added successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void updateMedicalRecord(MedicalRecord record) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("UPDATE medical_records SET diagnosis_details = '" + record.getDiagnosis()
						+ "', staff_id = " + record.getStaff_id() + ", checkin_id = " + record.getCheckin_id()
						+ "  WHERE id = " + record.getId());
				System.out.println("Record updated successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void deleteMedicalRecord(int record_id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("DELETE from medical_records WHERE id = " + record_id + ";");
				System.out.println("Record deleted successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public List<MedicalRecord> viewAllMedicalRecords() {
		List<MedicalRecord> records = new ArrayList<>();
		String patient_name = "", doctor_name = "";
		try {
			connection = getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * from medical_records");
			while (rs.next()) {
				int id = rs.getInt("id");
				String diagnosis = rs.getString("diagnosis_details");
				int checkin_id = rs.getInt("checkin_id");
				int staff_id = rs.getInt("staff_id");
				ResultSet rs1 = statement.executeQuery("SELECT patient_id from checkins WHERE id = " + checkin_id);
				while (rs1.next()) {
					int patient_id = rs1.getInt("patient_id");
					ResultSet rs2 = statement.executeQuery("SELECT name from patients WHERE id = " + patient_id);
					while (rs2.next()) {
						patient_name = rs2.getString("name");
					}
				}
				ResultSet rs3 = statement.executeQuery("SELECT name from staff WHERE id = " + staff_id);
				while (rs3.next()) {
					doctor_name = rs3.getString("name");
				}
				records.add(new MedicalRecord(id, diagnosis, checkin_id, patient_name, staff_id, doctor_name));
			}

		} catch (Throwable oops) {
			oops.printStackTrace();
		}

		return records;

	}

	public MedicalRecord selectMedicalRecord(int id) {
		MedicalRecord record = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(
						"SELECT id, diagnosis_details, checkin_id, staff_id FROM medical_records where id =" + id + "");

				while (rs.next()) {
					String diagnosis = rs.getString("diagnosis_details");
					int checkin_id = rs.getInt("checkin_id");
					int staff_id = rs.getInt("staff_id");
					record = new MedicalRecord(id, diagnosis, checkin_id, staff_id);
				}
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return record;
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
