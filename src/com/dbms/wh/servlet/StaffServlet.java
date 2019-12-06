package com.dbms.wh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbms.wh.bean.Patient;
import com.dbms.wh.bean.Staff;
import com.dbms.wh.dao.StaffDAO;

@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public StaffServlet() {
        super();
    }
	
	private StaffDAO staffdao;

	public void init() {
		staffdao = new StaffDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String option = request.getParameter("operation");
		System.out.println(option);
		if (option == null) {
			option = "LIST";
		}
		
		try {
			switch (option) {
			case "ADD":
				showNewForm(request, response);
				break;
			case "INSERT":
				insertStaff(request, response);
				break;
			case "DELETE":
				deleteStaff(request, response);
				break;
			case "EDIT":
				showEditForm(request, response);
				break;
			case "UPDATE":
				updateStaff(request, response);
				break;
			case "LIST":
				listStaff(request, response);
				break;
			case "STATS":
				statDisplay(request, response);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listStaff(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Staff> listStaff = staffdao.selectAllStaff();
		request.setAttribute("listStaff", listStaff);
		RequestDispatcher dispatcher = request.getRequestDispatcher("staff-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/staff-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Staff existingStaff = staffdao.selectStaff(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/staff-form.jsp");
		request.setAttribute("staff", existingStaff);
		dispatcher.forward(request, response);
	}

	private void insertStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		Staff newStaff = new Staff(request.getParameter("name"), Integer.parseInt(request.getParameter("age")),
				request.getParameter("gender"), request.getParameter("job_title"),
				request.getParameter("professional_title"), request.getParameter("phone_no"),
				request.getParameter("address"), request.getParameter("department"));
		staffdao.insertStaff(newStaff);
		response.sendRedirect("StaffServlet");
	}

	private void updateStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Staff staff = new Staff(request.getParameter("name"), Integer.parseInt(request.getParameter("age")),
				request.getParameter("gender"), request.getParameter("job_title"),
				request.getParameter("professional_title"), request.getParameter("phone_no"),
				request.getParameter("address"), request.getParameter("department"));
		staff.setId(id);
		staffdao.updateStaff(staff);
		response.sendRedirect("StaffServlet");
	}

	private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		staffdao.deleteStaff(id);
		response.sendRedirect("StaffServlet");
	}
	
	private void statDisplay(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		String doc = request.getParameter("id");
		if(doc != null) {
			List<Patient> patients = staffdao.selectPatientUnderADoctor(Integer.parseInt(doc));
			request.setAttribute("patients", patients);
		}
		System.out.println("Inside Info of staffs grouped by role!");
		List<Staff> staffOrderbyRole = staffdao.staffOrderbyRole();
		request.setAttribute("staffOrderbyRole", staffOrderbyRole);
		List<Staff> listStaff = staffdao.selectAllStaff();
		request.setAttribute("listStaff", listStaff);
		RequestDispatcher dispatcher = request.getRequestDispatcher("staff-list.jsp");
		dispatcher.forward(request, response);
	}
}
