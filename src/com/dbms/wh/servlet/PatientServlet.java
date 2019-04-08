package com.dbms.wh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			if(request.getParameter("operation").equals("create")) {
				// Converting date from Java to SQL
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date parsed = format.parse(request.getParameter("dob"));
				Patient patient = new Patient(Integer.parseInt(request.getParameter("age")), request.getParameter("name"), request.getParameter("ssn"), request.getParameter("phoneNo"), request.getParameter("gender"), parsed, request.getParameter("address"));
				patientdao.createPatient(patient);
			}
			else if(request.getParameter("operation").equals("update")) {
				
			}
			else if(request.getParameter("operation").equals("delete")) {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
