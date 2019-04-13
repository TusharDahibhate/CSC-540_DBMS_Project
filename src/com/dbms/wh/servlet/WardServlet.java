package com.dbms.wh.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbms.wh.bean.Ward;
import com.dbms.wh.dao.WardDAO;

@WebServlet("/WardServlet")
public class WardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private WardDAO wardDAO;

	public void init() {
		wardDAO = new WardDAO();
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
				insertWard(request, response);
				break;
			case "DELETE":
				deleteWard(request, response);
				break;
			case "EDIT":
				showEditForm(request, response);
				break;
			case "UPDATE":
				updateWard(request, response);
				break;
			case "LIST":
				listWard(request, response);
				break;
			case "usgpercent":
				usgpercent(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void usgpercent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		LinkedHashMap <Integer, Float> usgpercentward = wardDAO.getUsagePercent();
		request.setAttribute("usgpercentward", usgpercentward);
		List<Ward> listWard = wardDAO.selectAllWards();
		request.setAttribute("listWard", listWard);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ward-list.jsp");
		dispatcher.forward(request, response);
	}

	private void listWard(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Ward> listWard = wardDAO.selectAllWards();
		request.setAttribute("listWard", listWard);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ward-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ward-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Ward existingWard = wardDAO.selectWard(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ward-form.jsp");
		request.setAttribute("ward", existingWard);
		dispatcher.forward(request, response);
	}

	private void insertWard(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int staff_id = Integer.parseInt(request.getParameter("staff_id"));
		int type = Integer.parseInt(request.getParameter("type"));

		Ward newWard = new Ward(staff_id, type);
		wardDAO.insertWard(newWard);
		response.sendRedirect("WardServlet");
	}

	private void updateWard(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int staff_id = Integer.parseInt(request.getParameter("staff_id"));
		int type = Integer.parseInt(request.getParameter("type"));

		Ward newWard = new Ward(id, staff_id, type);
		wardDAO.updateWard(newWard);
		response.sendRedirect("WardServlet");
	}

	private void deleteWard(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		wardDAO.deleteWard(id);
		response.sendRedirect("WardServlet");
	}

}
