package com.dbms.wh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
