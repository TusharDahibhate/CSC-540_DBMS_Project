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

import com.dbms.wh.bean.CheckIn;
import com.dbms.wh.bean.MedicalRecord;
import com.dbms.wh.bean.Staff;
import com.dbms.wh.bean.Test;
import com.dbms.wh.dao.CheckInDAO;
import com.dbms.wh.dao.MedicalRecordDAO;
import com.dbms.wh.dao.StaffDAO;

@WebServlet("/MedicalRecordServlet")
public class MedicalRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MedicalRecordDAO recordDAO = new MedicalRecordDAO();
	StaffDAO staffDAO = new StaffDAO();
	CheckInDAO checkinDAO = new CheckInDAO();
	public MedicalRecordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String option = request.getParameter("operation");

		System.out.println(option);
		if (option == null) {
			option = "list";
		}

		try {
			switch (option) {
			case "add":
				addMedicalRecord(request, response);
				break;
			case "create":
				createMedicalRecord(request, response);
				break;
			case "delete":
				deleteMedicalRecord(request, response);
				break;
			case "modify":
				modifyMedicalRecord(request, response);
				break;
			case "update":
				updateMedicalRecord(request, response);
				break;
			case "list":
				listMedicalRecord(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}
	
	private void addMedicalRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		try {
			
			List<Staff> staffs = staffDAO.selectAllDoctors();
			request.setAttribute("staffs", staffs);
			List<CheckIn> checkins = checkinDAO.selectAllCheckin();
			request.setAttribute("checkins", checkins);
			RequestDispatcher dispatcher = request.getRequestDispatcher("record-form.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void createMedicalRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		try {
			int staff_id = Integer.parseInt(request.getParameter("staff_id"));
			int price = Integer.parseInt(request.getParameter("price"));
			String diagnosis = request.getParameter("diagnosis");
			MedicalRecord record = new MedicalRecord(diagnosis, price, staff_id);
			recordDAO.createMedicalRecord(record);
			List<MedicalRecord> records = recordDAO.viewAllMedicalRecords();
			request.setAttribute("records", records);
			RequestDispatcher dispatcher = request.getRequestDispatcher("record-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateMedicalRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			List<Staff> staffs = staffDAO.selectAllDoctors();
			request.setAttribute("staffs", staffs);
			List<CheckIn> checkins = checkinDAO.selectAllCheckin();
			request.setAttribute("checkins", checkins);
			MedicalRecord existingRecord = recordDAO.selectMedicalRecord(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/record-form.jsp");
			request.setAttribute("record", existingRecord);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void modifyMedicalRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String diagnosis = request.getParameter("diagnosis");
		int checkin_id = Integer.parseInt(request.getParameter("checkin_id"));
		int staff_id = Integer.parseInt(request.getParameter("staff_id"));
		int id = Integer.parseInt(request.getParameter("id"));
		MedicalRecord record = new MedicalRecord(id, diagnosis, checkin_id, staff_id);
		recordDAO.updateMedicalRecord(record);
		List<MedicalRecord> records = recordDAO.viewAllMedicalRecords();
		request.setAttribute("records", records);
		RequestDispatcher dispatcher = request.getRequestDispatcher("record-list.jsp");
		dispatcher.forward(request, response);

	}

	private void deleteMedicalRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			recordDAO.deleteMedicalRecord(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listMedicalRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<MedicalRecord> records = recordDAO.viewAllMedicalRecords();
		request.setAttribute("records", records);
		RequestDispatcher dispatcher = request.getRequestDispatcher("record-list.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
