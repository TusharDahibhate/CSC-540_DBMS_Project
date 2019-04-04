package com.dbms.wh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Patient")
public class Patient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";

	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;

	public Patient() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		String option = request.getParameter("button");

		if (option == "CREATE") {
			String name = "Tanmaya";
			Date dob = new Date();
			String gender = "F";
			String SSN = "1234567";
			String address = "512 Tartan Circle";
			String phoneNo = "12345678";
			String age = "23";
			createPatient(name, dob, gender, SSN, address, phoneNo, age);

		}

		if (option == "UPDATE") {
			String id = request.getParameter("id");
			String n = request.getParameter("name");
			String d = request.getParameter("dob");
			String g = request.getParameter("gender");
			String s = request.getParameter("SSN");
			String a = request.getParameter("address");
			String ph = request.getParameter("phoneNo");
			String age = request.getParameter("age");
			updatePatient(id, n, d, g, s, a, ph, age);
		}

		if (option == "DELETE") {
			String id = request.getParameter("id");
			deletePatient(id);
		}

		if (option == "VIEW") {
			String id = request.getParameter("id");
			viewPatientRecord(id);
		}
		if (option == "VIEW ALL") {
			viewAllPatient();
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

	protected void createPatient(String name, Date dob, String gender, String SSN, String address, String phoneNo,
			String age) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {

				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();

				statement.executeUpdate("INSERT INTO Patients VALUES (name, dob, gender, ssn, address, phone_no, age)"
						+ "(" + name + "," + dob + "," + gender + "," + SSN + "," + address + "," + phoneNo + "," + age
						+ ")");

				result = statement
						.executeQuery("SELECT id, name FROM Patients WHERE name =" + name + "AND SSN = " + SSN);

				while (result.next()) {
					String pname = result.getString("name");
					float pSSN = result.getFloat("SSN");
					System.out.println("New Patient added with " + pname + "  " + pSSN);
				}
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	protected void updatePatient(String id, String name, String dob, String gender, String SSN, String address,
			String phoneNo, String age) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {

				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();

				statement.executeUpdate("UPDATE Patients SET VALUES" + "(" + name + "," + dob + "," + gender + "," + SSN
						+ "," + address + "," + phoneNo + "," + age + ") WHERE ID = " + id);

				result = statement
						.executeQuery("SELECT id, name FROM Patients WHERE name =" + name + "AND SSN = " + SSN);

				while (result.next()) {
					String pname = result.getString("name");
					float pSSN = result.getFloat("SSN");
					System.out.println("Patient record with " + pname + "  " + pSSN + "updated");
				}
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}

	}

	protected void deletePatient(String id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {

				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();

				statement.executeUpdate("DELETE FROM PATIENTS WHERE ID = " + id);

				result = statement.executeQuery("SELECT id, name FROM Patients WHERE id =" + id);

				while (result.next()) {
					String pname = result.getString("name");
					float pSSN = result.getFloat("SSN");
					System.out.println("Patient record with " + pname + "  " + pSSN + "deleted");
				}
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	protected void viewPatientRecord(String id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {

				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();

				statement.executeUpdate("SELECT * FROM PATIENTS WHERE ID = " + id);

				result = statement.executeQuery("SELECT id, name FROM Patients WHERE id =" + id);

				// code logic to print goes here
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	protected void viewAllPatient() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {

				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();

				statement.executeUpdate("SELECT * FROM PATIENTS");

				// code logic to print to browser goes here

			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
