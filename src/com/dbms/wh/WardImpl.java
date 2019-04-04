package com.dbms.wh;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WardImpl")
public class WardImpl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	private static final String user = "cagarwa3";
	private static final String password = "200234585";
    public WardImpl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter( );
		String staff_id = request.getParameter("staff_id");
		String type = request.getParameter("type");
		out.println("Here!" + staff_id + type);
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection connection = null;
            Statement statement = null;
            ResultSet result = null;

            try {
	            connection = DriverManager.getConnection(jdbcURL, user, password);
	            statement = connection.createStatement();
	            if(staff_id != null && type != null) {
	            	statement.executeUpdate("INSERT INTO wards(staff_id, type) VALUES ("+ staff_id +", " + type + ");");
	            }
	            // Get records from the CATS table
//		        result = statement.executeQuery("SELECT staff_id, type FROM wards");
//		        while (result.next()) {
//		            String name = result.getString("staff_id");
//		            float weight = result.getFloat("type");
//		            System.out.println(name + "  " + weight);
//		        }
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
