package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dbms.wh.bean.Prescription;

public class PrescriptionDAO {

	private String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	private String jdbcUsername = "cagarwa3";
	private String jdbcPassword = "200234585";

	private static final String INSERT_PRESCRIPTIONS_SQL = "INSERT INTO prescriptions(quantity, medication_id,  record_id) VALUES "
			+ " (?, ?, ?);";
	private static final String SELECT_PRESCRIPTIONS_BY_ID = "SELECT * FROM prescriptions where id =?;";
	private static final String SELECT_ALL_PRESCRIPTIONS = "SELECT * FROM prescriptions";
	private static final String DELETE_PRESCRIPTIONS_SQL = "DELETE FROM prescriptions WHERE id = ?;";
	private static final String UPDATE_PRESCRIPTIONS_SQL = "UPDATE prescriptions SET quantity = ?,medication_id= ?, record_id= ? where id =?;";

	public PrescriptionDAO() {
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

	public void insertPrescription(Prescription prescription) throws SQLException {
		System.out.println(INSERT_PRESCRIPTIONS_SQL);

		try {
			Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRESCRIPTIONS_SQL);
			preparedStatement.setInt(1, prescription.getQuantity());
			preparedStatement.setInt(2, prescription.getMedicationId());
			preparedStatement.setInt(3, prescription.getRecordId());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Prescription selectPrescription(int id) {
		Prescription prescription = null;

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRESCRIPTIONS_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int quantity = rs.getInt("quantity");				
				int medication_id = rs.getInt("medication_id");
				int record_id = rs.getInt("record_id");
				prescription = new Prescription(id, quantity, medication_id, record_id);
			}			
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prescription;
	}
	
	public int getBill(int checkin_id) {

		try (Connection connection = getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement("select sum(m.price * p.quantity) as total from medical_records mr, prescriptions p, medications m where mr.checkin_id=? AND mr.id=p.record_id AND p.medication_id=m.id;");) {
			preparedStatement.setInt(1, checkin_id);
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				return rs.getInt("total");
				
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return 0;
	}

	public List<Prescription> selectAllPrescriptions() {

		List<Prescription> prescriptions = new ArrayList<>();

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRESCRIPTIONS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int quantity = rs.getInt("quantity");
				;
				int medication_id = rs.getInt("medication_id");
				int record_id = rs.getInt("record_id");
				prescriptions.add(new Prescription(id, quantity, medication_id, record_id));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return prescriptions;
	}

	public boolean deletePrescription(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PRESCRIPTIONS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updatePrescription(Prescription prescription) throws SQLException {
		boolean rowUpdated = false;
		try {
			Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_PRESCRIPTIONS_SQL);
			statement.setInt(1, prescription.getQuantity());
			statement.setInt(2, prescription.getMedicationId());
			statement.setInt(3, prescription.getRecordId());
			statement.setInt(4, prescription.getId());

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
