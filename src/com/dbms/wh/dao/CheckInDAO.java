package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.util.Date;

import com.dbms.wh.bean.CheckIn;

public class CheckInDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public CheckInDAO() {

	}

	public void createCheckIn(CheckIn checkin) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				java.sql.Date startDate = new java.sql.Date(checkin.getStartDate().getTime());
				java.sql.Date endDate = checkin.getEndDate() != null ? new java.sql.Date(checkin.getEndDate().getTime()) : null;
				statement.executeUpdate("INSERT INTO checkins (patient_id, staff_id, start_date, end_date) VALUES"
						+ "(" + checkin.getPatientId() + "," + checkin.getStaffId() + ",'" + startDate + "','"
						+ endDate + "')");
				System.out.println("New checkin added successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void updateCheckIn(CheckIn checkin) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				java.sql.Date startDate = new java.sql.Date(checkin.getStartDate().getTime());
				java.sql.Date endDate = checkin.getEndDate() != null ? new java.sql.Date(checkin.getEndDate().getTime()) : null;
				
				statement.executeUpdate("UPDATE checkins SET patient_id = " + checkin.getPatientId() + ", staff_id = " + checkin.getStaffId()
						+ ", start_date = '" + startDate + "', end_date = '" + endDate + "'  WHERE id = " + checkin.getId() + ";");
				System.out.println("Checkin updated successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void deleteCheckIn(int checkinId) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("DELETE from checkins where id = " + checkinId + ";");
				System.out.println("Checkin deleted successfully!");
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
