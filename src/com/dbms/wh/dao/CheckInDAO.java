package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
//import java.util.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.dbms.wh.bean.CheckIn;
import com.dbms.wh.bean.Staff;

public class CheckInDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	private static final String INSERT_CHECKIN_SQL = "INSERT INTO checkins (patient_id, staff_id, start_date, end_date) VALUES " + " (?, ?, ?, ?);";
	private static final String SELECT_CHECKIN_BY_ID = "SELECT * FROM checkins where id =?";
	private static final String SELECT_ALL_CHECKIN = "SELECT * FROM checkins";
	private static final String DELETE_CHECKIN_SQL = "DELETE FROM checkins WHERE id = ?;";
	private static final String UPDATE_CHECKIN_SQL = "UPDATE checkins SET patient_id = ?,staff_id= ?, start_date = ?, end_date = ? WHERE id = ?;";
	
	public CheckInDAO() {

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
	
	public void insertCheckin(CheckIn checkin) throws SQLException {
		
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CHECKIN_SQL)) {
			preparedStatement.setInt(1, checkin.getPatientid());
			preparedStatement.setInt(2, checkin.getStaffid());
			if(checkin.getStartdate() != null)
				preparedStatement.setDate(3, new java.sql.Date(checkin.getStartdate().getTime()));
			else
				preparedStatement.setNull(3, Types.DATE);
			
			if(checkin.getEnddate() != null)
				preparedStatement.setDate(4, new java.sql.Date(checkin.getEnddate().getTime()));
			else
				preparedStatement.setNull(4, Types.DATE);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public CheckIn selectCheckin(int id) {
		CheckIn checkin = null;

		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CHECKIN_BY_ID);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				checkin = new CheckIn(rs.getInt("patient_id"), rs.getInt("staff_id"), rs.getDate("start_date"), rs.getDate("end_date"));
				checkin.setId(id);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return checkin;
	}
	
	public LinkedHashMap<Integer, Integer> getPatientsByMonth(){
		LinkedHashMap<Integer, Integer> lhm = new LinkedHashMap<Integer, Integer>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select MONTH(start_date), count(id) from checkins group by MONTH(start_date);");
				while (rs.next()) {
					lhm.put(rs.getInt(1), rs.getInt(2));
				}
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return lhm;
	}
	
	public int getCurrentlyCheckedinPatients() {
		int total = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("select count(id) from checkins where ( end_date > DATE(NOW()) AND start_date <= DATE(NOW()) ) OR (start_date <= DATE(NOW()) AND end_date IS NULL);");
				while (rs.next()) {
					total = rs.getInt(1);
				}
				
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return total;
	}
	
	
	public List<CheckIn> selectAllCheckin() {

		List<CheckIn> checkin = new ArrayList<>();

		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CHECKIN);) {
			ResultSet rs = preparedStatement.executeQuery();
			CheckIn s;
			while (rs.next()) {
				s = new CheckIn(rs.getInt("patient_id"), rs.getInt("staff_id"), rs.getDate("start_date"), rs.getDate("end_date"));
				s.setId(rs.getInt("id"));
				checkin.add(s);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return checkin;
	}
	
	public boolean deleteCheckin(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_CHECKIN_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateCheckin(CheckIn checkin) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CHECKIN_SQL);) {
			preparedStatement.setInt(1, checkin.getPatientid());
			preparedStatement.setInt(2, checkin.getStaffid());
			if(checkin.getStartdate() != null)
				preparedStatement.setDate(3, new java.sql.Date(checkin.getStartdate().getTime()));
			else
				preparedStatement.setNull(3, Types.DATE);
			
			if(checkin.getEnddate() != null)
				preparedStatement.setDate(4, new java.sql.Date(checkin.getEnddate().getTime()));
			else
				preparedStatement.setNull(4, Types.DATE);
			preparedStatement.setInt(5, checkin.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean checkout(int checkin_id) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE checkins SET end_date = DATE(NOW()) WHERE id = ?;");) {
			preparedStatement.setInt(1, checkin_id);
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