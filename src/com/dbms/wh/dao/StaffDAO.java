package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dbms.wh.bean.Staff;

public class StaffDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	private static final String INSERT_STAFF_SQL = "INSERT INTO staff(name, age, gender, job_title, professional_title, phone_no, address, department) VALUES " + " (?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_STAFF_BY_ID = "SELECT * FROM staff where id =?";
	private static final String SELECT_ALL_STAFF = "SELECT * FROM staff";
	private static final String DELETE_STAFF_SQL = "DELETE FROM staff WHERE id = ?;";
	private static final String UPDATE_STAFF_SQL = "UPDATE staff SET name = ?,age= ?, gender = ?, job_title = ?, professional_title = ?, phone_no = ?, address = ?, department = ? WHERE id = ?;";
	
	public StaffDAO() {

	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertStaff(Staff staff) throws SQLException {
		
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STAFF_SQL)) {
			preparedStatement.setString(1, staff.getName());
			preparedStatement.setInt(2, staff.getAge());
			preparedStatement.setString(3, staff.getGender());
			preparedStatement.setString(4, staff.getJobtitle());
			preparedStatement.setString(5, staff.getProfessionaltitle());
			preparedStatement.setInt(6, staff.getPhoneno());
			preparedStatement.setString(7, staff.getAddress());
			preparedStatement.setString(8, staff.getDepartment());
			

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Staff selectStaff(int id) {
		Staff staff = null;

		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STAFF_BY_ID);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				staff = new Staff(rs.getString("name"), rs.getInt("age"), rs.getString("gender"), rs.getString("job_title"), rs.getString("professional_title"), rs.getInt("phone_no"), rs.getString("address"), rs.getString("department"));
				staff.setId(id);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return staff;
	}
	
	public List<Staff> selectAllStaff() {

		List<Staff> staff = new ArrayList<>();

		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STAFF);) {
			ResultSet rs = preparedStatement.executeQuery();
			Staff s;
			while (rs.next()) {
				s = new Staff(rs.getString("name"), rs.getInt("age"), rs.getString("gender"), rs.getString("job_title"), rs.getString("professional_title"), rs.getInt("phone_no"), rs.getString("address"), rs.getString("department"));
				s.setId(rs.getInt("id"));
				staff.add(s);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return staff;
	}
	
	public boolean deleteStaff(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_STAFF_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateStaff(Staff staff) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STAFF_SQL);) {
			preparedStatement.setString(1, staff.getName());
			preparedStatement.setInt(2, staff.getAge());
			preparedStatement.setString(3, staff.getGender());
			preparedStatement.setString(4, staff.getJobtitle());
			preparedStatement.setString(5, staff.getProfessionaltitle());
			preparedStatement.setInt(6, staff.getPhoneno());
			preparedStatement.setString(7, staff.getAddress());
			preparedStatement.setString(8, staff.getDepartment());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
