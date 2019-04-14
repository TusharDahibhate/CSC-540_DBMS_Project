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
import com.dbms.wh.bean.Report;
import com.dbms.wh.bean.Staff;
import com.dbms.wh.bean.TestReport;
import com.dbms.wh.bean.Ward;
import com.dbms.wh.dao.CheckInDAO;
import com.dbms.wh.dao.StaffDAO;
import com.dbms.wh.dao.BedDAO;
import com.dbms.wh.dao.PrescriptionDAO;
import com.dbms.wh.dao.ReportDAO;
import com.dbms.wh.dao.TestReportDAO;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int regFee = 100;
	public CheckoutServlet() {
		super();
	}

	private CheckInDAO checkindao;
	private BedDAO beddao;
	private PrescriptionDAO pdao;
	private TestReportDAO tdao;
	private CheckIn checkin;
	private ReportDAO reportDAO;
	private Report wardbill;
	private TestReportDAO testDAO;

	public void init() {
		checkindao = new CheckInDAO();
		beddao = new BedDAO();
		pdao = new PrescriptionDAO();
		tdao = new TestReportDAO();
		reportDAO = new ReportDAO();
		testDAO = new TestReportDAO();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String option = request.getParameter("operation");
		if (option == null) {
			option = "CHECKOUT";
		}
		try {
			switch (option) {
			case "CHECKOUT":
				displayBill(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void displayBill(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<TestReport> testbill = testDAO.selectPatientTestReport(id);
		checkindao.checkout(id);
		checkindao = new CheckInDAO();
		checkin = checkindao.selectCheckin(id);
		int rate = reportDAO.createWardBill(id);
		wardbill = new Report(checkin.getPatientid(), checkin.getId(), checkin.getStartdate(), checkin.getEnddate(),
				rate);
		request.setAttribute("wardbill", wardbill);
		List<Report> prescbill = reportDAO.getPrescriptionBill(id);
		request.setAttribute("prescbill", prescbill);
		request.setAttribute("testbill", testbill);
		beddao.unassignBed(id);
		int pBill = pdao.getBill(id);
		int tBill = tdao.getBill(id);
		int bBill = beddao.getBill(id);
		request.setAttribute("pBill", pBill);
		request.setAttribute("tBill", tBill);
		request.setAttribute("bBill", bBill);
		
		request.setAttribute("total", tBill + pBill + bBill + regFee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("checkout-done.jsp");
		dispatcher.forward(request, response);
	}

}
