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

import com.dbms.wh.bean.Patient;
import com.dbms.wh.bean.Test;
import com.dbms.wh.dao.TestDAO;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestDAO testDAO = new TestDAO();

	public TestServlet() {
		super();
	}

	public void init() {
		testDAO = new TestDAO();
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
			case "create":
				createTest(request, response);
				break;
			case "delete":
				deleteTest(request, response);
				break;
			case "modify":
				modifyTest(request, response);
				break;
			case "update":
				updateTest(request, response);
				break;
			case "list":
				listTest(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	private void createTest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		try {
			int staff_id = Integer.parseInt(request.getParameter("staff_id"));
			int price = Integer.parseInt(request.getParameter("price"));
			Test test = new Test(request.getParameter("name"), price, staff_id);
			testDAO.createTest(test);
			List<Test> tests = testDAO.viewAllTests();
			request.setAttribute("tests", tests);
			RequestDispatcher dispatcher = request.getRequestDispatcher("test-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateTest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			Test existingTest = testDAO.selectTest(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("test-form.jsp");
			request.setAttribute("test", existingTest);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void modifyTest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		int staff_id = Integer.parseInt(request.getParameter("staff_id"));
		int price = Integer.parseInt(request.getParameter("price"));
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		Test test = new Test(id, name, price, staff_id);
		testDAO.updateTest(test);
		List<Test> tests = testDAO.viewAllTests();
		request.setAttribute("tests", tests);
		RequestDispatcher dispatcher = request.getRequestDispatcher("test-list.jsp");
		dispatcher.forward(request, response);

	}

	private void deleteTest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			testDAO.deleteTest(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listTest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Test> tests = testDAO.viewAllTests();
		request.setAttribute("tests", tests);
		RequestDispatcher dispatcher = request.getRequestDispatcher("test-list.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
