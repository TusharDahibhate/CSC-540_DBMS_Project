package com.dbms.wh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbms.wh.bean.CheckIn;
import com.dbms.wh.bean.Test;
import com.dbms.wh.bean.TestReport;
import com.dbms.wh.dao.CheckInDAO;
import com.dbms.wh.dao.TestDAO;
import com.dbms.wh.dao.TestReportDAO;

@WebServlet("/TestReportServlet")
public class TestReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestReportDAO testReportDAO;
	private CheckInDAO checkinDAO;
	private TestDAO testDAO;
	public TestReportServlet() {
		super();
	}

	public void init() {
		testReportDAO = new TestReportDAO();
		checkinDAO = new CheckInDAO();
		testDAO = new TestDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		String option = request.getParameter("operation");

		System.out.println(option);
		if (option == null) {
			option = "list";
		}

		try {
			switch (option) {
			case "add":
				addTestReport(request, response);
				break;
			case "create":
				createTestReport(request, response);
				break;
			case "delete":
				deleteTestReport(request, response);
				break;
			case "modify":
				modifyTestReport(request, response);
				break;
			case "update":
				updateTestReport(request, response);
				break;
			case "list":
				listTestReport(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}
	
	private void addTestReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		try {
			
			List<CheckIn> checkins = checkinDAO.selectAllCheckin();
			request.setAttribute("checkins", checkins);
			List<Test> tests = testDAO.viewAllTests();
			request.setAttribute("tests", tests);
			RequestDispatcher dispatcher = request.getRequestDispatcher("report-form.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createTestReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		try {
			int checkin_id = Integer.parseInt(request.getParameter("checkin_id"));
			int test_id = Integer.parseInt(request.getParameter("test_id"));
			String result = request.getParameter("result");
			TestReport report = new TestReport(checkin_id, test_id, result);
			testReportDAO.createTestReport(report);
			List<TestReport> reports = testReportDAO.viewAllTestReports();
			request.setAttribute("reports", reports);
			RequestDispatcher dispatcher = request.getRequestDispatcher("report-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateTestReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			List<CheckIn> checkins = checkinDAO.selectAllCheckin();
			request.setAttribute("checkins", checkins);
			List<Test> tests = testDAO.viewAllTests();
			request.setAttribute("tests", tests);
			TestReport existingTestReport = testReportDAO.selectTestReport(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("report-form.jsp");
			request.setAttribute("report", existingTestReport);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void modifyTestReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int checkin_id = Integer.parseInt(request.getParameter("checkin_id"));
		int test_id = Integer.parseInt(request.getParameter("test_id"));
		String result = request.getParameter("result");
		int id = Integer.parseInt(request.getParameter("id"));
		String patient_name = request.getParameter("patient_name");
		String test_name = request.getParameter("test_name");
		TestReport report = new TestReport(id, checkin_id, patient_name, test_id, test_name, result);
		testReportDAO.updateTestReport(report);
		List<TestReport> reports = testReportDAO.viewAllTestReports();
		request.setAttribute("reports", reports);
		RequestDispatcher dispatcher = request.getRequestDispatcher("report-list.jsp");
		dispatcher.forward(request, response);

	}

	private void deleteTestReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			testReportDAO.deleteTestReport(id);
			List<TestReport> reports = testReportDAO.viewAllTestReports();
			request.setAttribute("reports", reports);
			RequestDispatcher dispatcher = request.getRequestDispatcher("report-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listTestReport(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<TestReport> reports = testReportDAO.viewAllTestReports();
		request.setAttribute("reports", reports);
		RequestDispatcher dispatcher = request.getRequestDispatcher("report-list.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
