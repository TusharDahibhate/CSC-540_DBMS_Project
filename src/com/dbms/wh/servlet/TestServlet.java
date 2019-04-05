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

import com.dbms.wh.bean.Test;
import com.dbms.wh.dao.TestDAO;



@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public TestServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		TestDAO patientdao = new TestDAO();
		
		try {
			Staff staff = new Staff(request.getParameter("staff_id"));
			Test test = new Test(Integer.parseInt(request.getParameter("name")), request.getParameter("price"), staff);
			PrintWriter out = response.getWriter();
			patientdao.createTest(test);
			//out.println(patient.toString());
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
