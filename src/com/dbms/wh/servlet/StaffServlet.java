package com.dbms.wh.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbms.wh.bean.Staff;
import com.dbms.wh.dao.StaffDAO;

/**
 * Servlet implementation class Staff
 */
@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StaffServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		StaffDAO staffdao = new StaffDAO();
		// String id = request.getParameter("id");
		try {
			Staff staff = new Staff(request.getParameter("name"), Integer.parseInt(request.getParameter("age")),
					request.getParameter("gender"), request.getParameter("job_title"),
					request.getParameter("professional_title"), Integer.parseInt(request.getParameter("phone_no")),
					request.getParameter("address"), request.getParameter("department"));
			//PrintWriter out = response.getWriter();
			staffdao.createStaff(staff);
			// out.println(patient.toString());

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
