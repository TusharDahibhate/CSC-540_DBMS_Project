package com.dbms.wh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbms.wh.bean.Bed;
import com.dbms.wh.bean.Patient;
import com.dbms.wh.dao.PatientDAO;

@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public PatientServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PatientDAO patientdao = new PatientDAO();
		PrintWriter out = response.getWriter();
		//String id = request.getParameter("id");
		try {
			
			String operation;
			if(request.getParameter("operation") == null)
				operation = "list";
			else
				operation = request.getParameter("operation");
			
			if(operation.equals("create")) {
				// Converting date from Java to SQL
				//System.out.println("Create Patient Called");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date parsed = format.parse(request.getParameter("dob"));
				Patient patient = new Patient(Integer.parseInt(request.getParameter("age")), request.getParameter("name"), request.getParameter("ssn"), request.getParameter("phoneNo"), request.getParameter("gender"), parsed, request.getParameter("address"));
				patientdao.createPatient(patient);
				List<Patient> patients = patientdao.selectAllUsers();
				request.setAttribute("patients", patients);
				RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
				dispatcher.forward(request, response);
				
			}
			else if(operation.equals("update")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Patient existingPatient = patientdao.selectPatient(id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/patient-form.jsp");
				request.setAttribute("patient", existingPatient);
				dispatcher.forward(request, response);
			}
			else if(operation.equals("delete")) {
				
			}
			else if(operation.equals("list")) {
				List<Patient> patients = patientdao.selectAllUsers();
				request.setAttribute("patients", patients);
				//System.out.println("No of patients: " + patients.size());
				RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("From Patient Servlet");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
