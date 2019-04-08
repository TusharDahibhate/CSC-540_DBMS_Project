package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dbms.wh.bean.Patient;
import com.dbms.wh.bean.Staff;

public class StaffDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public StaffDAO() {

	}

	public void createStaff(Staff staff) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate(
						"INSERT INTO staff(name, age, gender, job_title, professional_title, phone_no, address, department) VALUES ('"
								+ staff.getName() + "', " + staff.getAge() + ", '" + staff.getGender() + "', '"
								+ staff.getJobTitle() + "', '" + staff.getProfessionalTitle() + "', "
								+ staff.getPhoneNo() + ", '" + staff.getAddress() + "', '" + staff.getDepartment()
								+ "');");
				System.out.println("New Staff added successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void updatePatient(Staff st) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("UPDATE staff SET name = '" + st.getName() + "', age = " + st.getAge()
						+ ", gender = '" + st.getGender() + "', job_title = '" + st.getJobTitle()
						+ "', professional_title = '" + st.getProfessionalTitle() + "', phone_no = " + st.getPhoneNo()
						+ ", address = '" + st.getAddress() + "', department='" + st.getDepartment() + "'  WHERE id = "
						+ st.getId() + ";");
				System.out.println("Staff updated successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void deleteStaff(int staff_id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("DELETE from staff where id = " + staff_id + ";");
				System.out.println("Staff deleted successfully!");
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
