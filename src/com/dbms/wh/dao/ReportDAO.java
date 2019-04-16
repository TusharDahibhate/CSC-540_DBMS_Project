package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.dbms.wh.bean.CheckIn;
import com.dbms.wh.bean.Report;
import com.dbms.wh.bean.TestReport;

public class ReportDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	BedDAO bedDAO = new BedDAO();

	public ReportDAO() {

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

	public int createWardBill(int checkin_id) {
		int rate = 0;
		try {

			rate = bedDAO.getBill(checkin_id);
		}

		catch (Throwable oops) {
			oops.printStackTrace();
		}
		return rate;
	}

	public List<Report> getPrescriptionBill(int checkin_id) {
		List<Report> pbills = new ArrayList<>();
		Report pbill;
		try {
			try {
				Connection connection = getConnection();
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(
						"SELECT c.patient_id, c.start_date, c.end_date, m.diagnosis_details, c.id, med.name, p.quantity, med.price from (checkins c inner join medical_records m  on  c.id = m.checkin_id inner join prescriptions p on p.record_id =  m.id inner join medications med on p.medication_id = med.id) WHERE c.id = "
								+ checkin_id);
				while (rs.next()) {
					int patient_id = rs.getInt("patient_id");
					int c_id = rs.getInt("id");
					Date start_date = rs.getDate("start_date");
					Date end_date = rs.getDate("start_date");
					String diagnosis = rs.getString("diagnosis_details");
					String med_name = rs.getString("name");
					int q = rs.getInt("quantity");
					int price = rs.getInt("price");
					pbill = new Report(patient_id, c_id, start_date, end_date, diagnosis, med_name, q, price);
					pbills.add(pbill);
				}

			} finally {
				close(result);
				close(statement);
				close(connection);
			}

		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return pbills;
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
