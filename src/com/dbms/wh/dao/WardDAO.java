package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbms.wh.bean.Ward;

public class WardDAO {

	private String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	private String jdbcUsername = "cagarwa3";
	private String jdbcPassword = "200234585";

	private static final String INSERT_WARDS_SQL = "INSERT INTO wards(staff_id, type) VALUES " + " (?, ?);";
	private static final String SELECT_WARD_BY_ID = "SELECT id, staff_id, type FROM wards where id =?";
	private static final String SELECT_ALL_WARDS = "SELECT * FROM wards";
	private static final String DELETE_WARDS_SQL = "DELETE FROM wards WHERE id = ?";
	private static final String UPDATE_WARDS_SQL = "UPDATE wards SET staff_id = ?,type = ? WHERE id = ?;";

	public WardDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertWard(Ward ward) throws SQLException {
		System.out.println(INSERT_WARDS_SQL);

		try {
			Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_WARDS_SQL);
			preparedStatement.setInt(1, ward.getStaffId());
			preparedStatement.setInt(2, ward.getType());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Ward selectWard(int id) {
		Ward ward = null;

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WARD_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int staff_id = rs.getInt("staff_id");
				int type = rs.getInt("type");
				ward = new Ward(id, staff_id, type);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return ward;
	}

	public List<Ward> selectAllWards() {

		List<Ward> wards = new ArrayList<>();

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_WARDS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int staff_id = rs.getInt("staff_id");
				int type = rs.getInt("type");
				wards.add(new Ward(id, staff_id, type));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return wards;
	}

	public boolean deleteWard(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_WARDS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateWard(Ward ward) throws SQLException {
		boolean rowUpdated = false;
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_WARDS_SQL);
			statement.setInt(1, ward.getStaffId());
			statement.setInt(2, ward.getType());
			statement.setInt(3, ward.getId());
			rowUpdated = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			printSQLException(e);
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
