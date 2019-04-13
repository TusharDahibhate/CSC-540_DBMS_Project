package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dbms.wh.bean.Test;
import com.dbms.wh.bean.TestReport;

public class TestReportDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public TestReportDAO() {

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

	public void createTestReport(TestReport testReport) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				Connection connection = getConnection();
				statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO test_reports (checkin_id, test_id, result) VALUES" + "("
						+ testReport.getCheckin_id() + "," + testReport.getTest_id() + ",'" + testReport.getResult()
						+ "')");
				System.out.println("New Test Report added successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void updateTestReport(TestReport testReport) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("UPDATE test_reports SET checkin_id = " + testReport.getCheckin_id() + ", test_id = "
						+ testReport.getTest_id() + ", result = '" + testReport.getResult() + "' WHERE id = "
						+ testReport.getId());
				System.out.println("New Test Report updated successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void deleteTestReport(int report_id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("DELETE from test_reports WHERE id = " + report_id + ";");
				System.out.println("New Test Report deleted successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public List<TestReport> viewAllTestReports() {
		List<TestReport> reports = new ArrayList<>();
		String patient_name = "", test_name = "";
		try {
			connection = getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * from test_reports");
			while (rs.next()) {
				int id = rs.getInt("id");
				int checkin_id = rs.getInt("checkin_id");
				int test_id = rs.getInt("test_id");
				String result = rs.getString("result");
				ResultSet rs1 = statement.executeQuery("SELECT patient_id from checkins WHERE id = " + checkin_id);
				while (rs1.next()) {
					int patient_id = rs1.getInt("patient_id");
					ResultSet rs2 = statement.executeQuery("SELECT name from patients WHERE id = " + patient_id);
					while (rs2.next()) {
						patient_name = rs2.getString("name");
					}
				}
				ResultSet rs3 = statement.executeQuery("SELECT name from tests WHERE id = " + test_id);
				while (rs3.next()) {
					test_name = rs3.getString("name");
				}
				reports.add(new TestReport(id, checkin_id, patient_name, test_id, test_name, result));
			}

		} catch (Throwable oops) {
			oops.printStackTrace();
		}

		return reports;

	}

	public TestReport selectTestReport(int id) {
		TestReport report = null;
		String patient_name = "", test_name = "";

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement
						.executeQuery("SELECT id, checkin_id, test_id, result FROM test_reports where id =" + id + "");

				while (rs.next()) {
					int checkin_id = rs.getInt("checkin_id");
					int test_id = rs.getInt("test_id");
					String result = rs.getString("result");
					ResultSet rs1 = statement.executeQuery("SELECT patient_id from checkins WHERE id = " + checkin_id);
					while (rs1.next()) {
						int patient_id = rs1.getInt("patient_id");
						ResultSet rs2 = statement.executeQuery("SELECT name from patients WHERE id = " + patient_id);
						while (rs2.next()) {
							patient_name = rs2.getString("name");
						}
					}
					ResultSet rs3 = statement.executeQuery("SELECT name from tests WHERE id = " + test_id);
					while (rs3.next()) {
						test_name = rs3.getString("name");
					}
					report = new TestReport(id, checkin_id, patient_name, test_id, test_name, result);
				}
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return report;
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
