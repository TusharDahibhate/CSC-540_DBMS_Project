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

import com.dbms.wh.bean.CheckIn;
import com.dbms.wh.dao.CheckInDAO;

@WebServlet("/CheckInServlet")
public class CheckInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckInServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		CheckInDAO checkindao = new CheckInDAO();
		PrintWriter out = response.getWriter();
		// String id = request.getParameter("id");
		try {
			if (request.getParameter("operation").equals("create")) {
				// Converting date from Java to SQL
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = format.parse(request.getParameter("start_date"));
				Date endDate = request.getParameter("end_date") != null ? format.parse(request.getParameter("end_date"))
						: null;
				CheckIn checkin = new CheckIn(Integer.parseInt(request.getParameter("patient_id")),
						Integer.parseInt(request.getParameter("staff_id")), startDate, endDate);
				checkindao.createCheckIn(checkin);
			} else if (request.getParameter("operation").equals("update")) {

			} else if (request.getParameter("operation").equals("delete")) {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
