package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbms.wh.bean.Bed;

public class BedDAO {

	private String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	private String jdbcUsername = "cagarwa3";
	private String jdbcPassword = "200234585";

	private static final String INSERT_BEDS_SQL = "INSERT INTO beds(ward_id, rate, checkin_id) VALUES " + " (?, ?, ?);";
	private static final String SELECT_BED_BY_ID = "SELECT id, ward_id, rate, checkin_id FROM beds where id =?";
	private static final String SELECT_ALL_BEDS = "SELECT * FROM beds";
	private static final String DELETE_BEDS_SQL = "DELETE FROM beds WHERE id = ?;";
	private static final String UPDATE_BEDS_SQL = "UPDATE beds SET rate = ?,checkin_id= ? WHERE id = ?;";

	public BedDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertBed(Bed bed) throws SQLException {
		System.out.println(INSERT_BEDS_SQL);

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BEDS_SQL)) {
			preparedStatement.setInt(1, bed.getWard_id());
			preparedStatement.setInt(2, bed.getRate());
			preparedStatement.setInt(3, bed.getCheckin_id());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Bed selectBed(int id) {
		Bed bed = null;

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BED_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int ward_id = rs.getInt("ward_id");
				int rate = rs.getInt("rate");
				int checkin_id = rs.getInt("checkin_id");
				bed = new Bed(id, ward_id, rate, checkin_id);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return bed;
	}

	public List<Bed> selectAllUsers() {

		List<Bed> beds = new ArrayList<>();

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BEDS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int ward_id = rs.getInt("ward_id");
				int rate = rs.getInt("rate");
				int checkin_id = rs.getInt("checkin_id");
				beds.add(new Bed(id, ward_id, rate, checkin_id));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return beds;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_BEDS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(Bed bed) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_BEDS_SQL);) {
			statement.setInt(1, bed.getRate());
			statement.setInt(2, bed.getCheckin_id());
			statement.setInt(3, bed.getId());

			rowUpdated = statement.executeUpdate() > 0;
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
