package com.dbms.wh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbms.wh.bean.CheckIn;
import com.dbms.wh.bean.Staff;
import com.dbms.wh.bean.Ward;
import com.dbms.wh.dao.CheckInDAO;
import com.dbms.wh.dao.StaffDAO;

@WebServlet("/CheckInServlet")
public class CheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckInServlet() {
        super();
    }
	
	private CheckInDAO checkindao;
	private StaffDAO staffdao;

	public void init() {
		checkindao = new CheckInDAO();
		staffdao = new StaffDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option = request.getParameter("operation");
		if (option == null) {
			option = "LIST";
		}
		
		try {
			switch (option) {
			case "ADD":
				showNewForm(request, response);
				break;
			case "INSERT":
				insertCheckin(request, response);
				break;
			case "DELETE":
				deleteCheckin(request, response);
				break;
			case "EDIT":
				showEditForm(request, response);
				break;
			case "UPDATE":
				updateCheckin(request, response);
				break;
			case "LIST":
				listCheckin(request, response);
				break;
			case "STATS":
				statDisplay(request, response);
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listCheckin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<CheckIn> listCheckin = checkindao.selectAllCheckin();
		request.setAttribute("listCheckin", listCheckin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("checkin-list.jsp");
		dispatcher.forward(request, response);
	}
	private void statDisplay(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		String month = request.getParameter("statMonth");
		if(month != null) {
			System.out.println("Inside month");
		}
		System.out.println("Inside Total Patiens Currently Checked In");
		int curr_checkins = checkindao.getCurrentlyCheckedinPatients();
		LinkedHashMap <Integer, Integer> patientsByMonth = checkindao.getPatientsByMonth();
		request.setAttribute("patientsByMonth", patientsByMonth);
		request.setAttribute("curr_checkins", curr_checkins);
		List<CheckIn> listCheckin = checkindao.selectAllCheckin();
		request.setAttribute("listCheckin", listCheckin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("checkin-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Staff> listStaff = staffdao.selectAllOperators();
		request.setAttribute("stafflist", listStaff);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/checkin-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		CheckIn existingCheckin = checkindao.selectCheckin(id);
		List<Staff> listStaff = staffdao.selectAllOperators();
		request.setAttribute("stafflist", listStaff);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/checkin-form.jsp");
		request.setAttribute("checkin", existingCheckin);
		dispatcher.forward(request, response);
	}

	private void insertCheckin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Converting date from Java to SQL
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null, endDate = null;
		try {
			startDate = format.parse(request.getParameter("start_date"));
			endDate = request.getParameter("end_date") != "" ? format.parse(request.getParameter("end_date"))
					: null;

			CheckIn checkin = new CheckIn(Integer.parseInt(request.getParameter("patient_id")),
					Integer.parseInt(request.getParameter("staff_id")), startDate, endDate);
			checkindao.insertCheckin(checkin);
			response.sendRedirect("CheckInServlet");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateCheckin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null, endDate = null;
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			startDate = format.parse(request.getParameter("start_date"));
			endDate = request.getParameter("end_date") != null ? format.parse(request.getParameter("end_date"))
					: null;

			CheckIn checkin = new CheckIn(Integer.parseInt(request.getParameter("patient_id")),
					Integer.parseInt(request.getParameter("staff_id")), startDate, endDate);
			checkin.setId(id);
			checkindao.updateCheckin(checkin);
			response.sendRedirect("CheckInServlet");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void deleteCheckin(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		checkindao.deleteCheckin(id);
		response.sendRedirect("CheckInServlet");
	}
}
