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

import com.dbms.wh.bean.Prescription;
import com.dbms.wh.dao.PrescriptionDAO;

@WebServlet("/PrescriptionServlet")
public class PrescriptionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PrescriptionDAO prescriptionDAO;

	public void init() {
		prescriptionDAO = new PrescriptionDAO();
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
				insertPrescription(request, response);
				break;
			case "DELETE":
				deletePrescription(request, response);
				break;
			case "EDIT":
				showEditForm(request, response);
				break;
			case "UPDATE":
				updatePrescription(request, response);
				break;
			case "LIST":
				listPrescriptions(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listPrescriptions(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Prescription> listPrescription = prescriptionDAO.selectAllPrescriptions();
		request.setAttribute("listPrescription", listPrescription);
		RequestDispatcher dispatcher = request.getRequestDispatcher("prescription-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/prescription-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Prescription existingPrescription = prescriptionDAO.selectPrescription(id);		
		request.setAttribute("prescription", existingPrescription);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/prescription-form.jsp");		
		dispatcher.forward(request, response);
	}

	private void insertPrescription(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int medication_id = Integer.parseInt(request.getParameter("medication_id"));
		int record_id = Integer.parseInt(request.getParameter("record_id"));

		Prescription newPrescription = new Prescription(quantity, medication_id, record_id);
		prescriptionDAO.insertPrescription(newPrescription);
		response.sendRedirect("PrescriptionServlet");
	}

	private void updatePrescription(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int medication_id = Integer.parseInt(request.getParameter("medication_id"));
		int record_id = Integer.parseInt(request.getParameter("record_id"));

		Prescription newPrescription = new Prescription(id, quantity, medication_id, record_id);
		prescriptionDAO.updatePrescription(newPrescription);
		response.sendRedirect("PrescriptionServlet");
	}

	private void deletePrescription(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		prescriptionDAO.deletePrescription(id);
		response.sendRedirect("PrescriptionServlet");
	}

}
