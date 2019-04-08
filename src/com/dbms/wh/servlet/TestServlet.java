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

import com.dbms.wh.bean.Test;
import com.dbms.wh.dao.TestDAO;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestDAO testDAO;

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
			case "ADD":
				getAddForm(request, response);
				break;
			case "INSERT":
				createTest(request, response);
				break;
			case "DELETE":
				deleteTest(request, response);
				break;
			case "EDIT":
				getEditForm(request, response);
				break;
			case "UPDATE":
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
		TestDAO testdao = new TestDAO();
		try {
			int staff_id = Integer.parseInt(request.getParameter("staff_id"));
			int price = Integer.parseInt(request.getParameter("price"));
			Test test = new Test(request.getParameter("name"), price, staff_id);
			PrintWriter out = response.getWriter();
			testdao.createTest(test);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void updateTest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		TestDAO testdao = new TestDAO();

		try {
			int staff_id = Integer.parseInt(request.getParameter("staff_id"));
			int price = Integer.parseInt(request.getParameter("price"));
			String name = request.getParameter("name");
			Test test = new Test(name, price, staff_id);
			PrintWriter out = response.getWriter();
			testdao.createTest(test);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteTest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		TestDAO testdao = new TestDAO();

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getAddForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/test-form.jsp");
		dispatcher.forward(request, response);
	}

	private void getEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Test test = testDAO.selectTest(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/test-form.jsp");
		request.setAttribute("test", test);
		dispatcher.forward(request, response);
	}

	private void listTest(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Test> testList = testDAO.viewAllTests();
		request.setAttribute("testList", testList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("test-list.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
