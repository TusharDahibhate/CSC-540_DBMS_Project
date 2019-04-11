package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dbms.wh.bean.Bed;
import com.dbms.wh.bean.Test;

public class TestDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public TestDAO() {

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

	public void createTest(Test test) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				Connection connection = getConnection();
				statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO tests (name, price, staff_id) VALUES" + "('" + test.getName()
						+ "','" + test.getName() + "','" + test.getStaff() + ")");
				System.out.println("New Test added successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void updateTest(Test test) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("UPDATE tests SET name = '" + test.getName() + "', price = " + test.getPrice()
						+ ", staff_id = " + test.getStaff_id() + " WHERE id = " + test.getId());
				System.out.println("New Test updated successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public void deleteTest(Test test) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("DELETE from tests WHERE id = " + "('" + test.getId() + ")");
				System.out.println("New Test deleted successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	public List<Test> viewAllTests() {
		List<Test> tests = new ArrayList<>();

		try {
			connection = getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * from tests");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int rate = rs.getInt("price");
				int staff_id = rs.getInt("staff_id");
				tests.add(new Test(id, name, rate, staff_id));
			}

		} catch (Throwable oops) {
			oops.printStackTrace();
		}

		return tests;

	}

	public Test selectTest(int id) {
		Test test = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement
						.executeQuery("SELECT id, name, price, staff_id FROM tests where id =" + id + "");

				while (rs.next()) {
					String name = rs.getString("name");
					int rate = rs.getInt("price");
					int staff_id = rs.getInt("staff_id");
					test = new Test(id, name, rate, staff_id);
				}
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return test;
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
