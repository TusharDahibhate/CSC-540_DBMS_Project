package com.dbms.wh;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/Sample")
public class Sample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	private static final String user = "cagarwa3";
	private static final String password = "200234585";
    public Sample() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter( );
		out.println("sdjfdhkf");
		
		try {

			Class.forName("org.mariadb.jdbc.Driver");

			Connection connection = null;
			            Statement statement = null;
			            ResultSet result = null;

			            try {

			            connection = DriverManager.getConnection(jdbcURL, user, password);
			            statement = connection.createStatement();
			            // statement.executeUpdate("CREATE TABLE CATS (CNAME VARCHAR(20), " +
			            // "TYPE VARCHAR(30), AGE INTEGER, WEIGHT FLOAT, SEX CHAR(1))");
			            // statement.executeUpdate("INSERT INTO CATS VALUES ('Oscar', 'Egyptian Mau'," +
			            // " 3, 23.4, 'F')");
			            // statement.executeUpdate("INSERT INTO CATS VALUES ('Max', 'Turkish Van Cats'," +
			            // " 2, 21.8, 'M')");
			            // statement.executeUpdate("INSERT INTO CATS VALUES ('Tiger', 'Russian Blue'," +
			            // " 1, 13.3, 'M')");
			            // statement.executeUpdate("INSERT INTO CATS VALUES ('Sam', 'Persian Cats'," +
			            // " 5, 24.3, 'M')");
			            // statement.executeUpdate("INSERT INTO CATS VALUES ('Simba', 'Americal Bobtail'," +
			            // " 3, 19.8, 'F')");
			            // statement.executeUpdate("INSERT INTO CATS VALUES ('Lucy', 'Turkish Angora Cats'," +
			            // "2, 22.4, 'F')");

			            // Get records from the CATS table
			        result = statement.executeQuery("SELECT staff_id, type FROM wards");

			        while (result.next()) {
			            String name = result.getString("staff_id");
			            float weight = result.getFloat("type");
			            System.out.println(name + "  " + weight);
			        }
			            } finally {
			                close(result);
			                close(statement);
			                close(connection);
			            }
			} catch(Throwable oops) {
			            oops.printStackTrace();
			        }
	}
	static void close(Connection connection) {
        if(connection != null) {
            try {
            connection.close();
            } catch(Throwable whatever) {}
        }
    }
    static void close(Statement statement) {
        if(statement != null) {
            try {
            statement.close();
            } catch(Throwable whatever) {}
        }
    }
    static void close(ResultSet result) {
        if(result != null) {
            try {
            result.close();
            } catch(Throwable whatever) {}
        }
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
