package com.dbms.wh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dbms.wh.bean.BillingAccount;
import com.dbms.wh.dao.BillingAccountDAO;

@WebServlet("/BillingAccountServlet")
public class BillingAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BillingAccountServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		BillingAccountDAO billingaccountdao = new BillingAccountDAO();
		PrintWriter out = response.getWriter();
		//String id = request.getParameter("id");
		try {
			
			String operation;
			if(request.getParameter("operation") == null)
				operation = "list";
			else
				operation = request.getParameter("operation");
			
			if(operation.equals("create")) {
				BillingAccount billingaccount = new BillingAccount(Integer.parseInt(request.getParameter("staff_id")), Integer.parseInt(request.getParameter("checkin_id")), Integer.parseInt(request.getParameter("paid_by_person")), Integer.parseInt(request.getParameter("paid_by_insurance")), request.getParameter("payment_info"), Integer.parseInt(request.getParameter("payee_ssn")), request.getParameter("billing_address"), Integer.parseInt(request.getParameter("total_charge")));
				billingaccountdao.createBillingAccount(billingaccount);
				List<BillingAccount> billingaccounts = billingaccountdao.selectAllAccounts();
				request.setAttribute("billingaccounts", billingaccounts);
				RequestDispatcher dispatcher = request.getRequestDispatcher("billingaccount-list.jsp");
				dispatcher.forward(request, response);
				
			}
			else if (operation.equals("modify")) {
				BillingAccount billingaccount = new BillingAccount(Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("staff_id")), Integer.parseInt(request.getParameter("checkin_id")), Integer.parseInt(request.getParameter("paid_by_person")), Integer.parseInt(request.getParameter("paid_by_insurance")), request.getParameter("payment_info"), Integer.parseInt(request.getParameter("payee_ssn")), request.getParameter("billing_address"), Integer.parseInt(request.getParameter("total_charge")));
				billingaccountdao.updateBillingAccount(billingaccount);
				List<BillingAccount> billingaccounts = billingaccountdao.selectAllAccounts();
				request.setAttribute("billingaccounts", billingaccounts);
				RequestDispatcher dispatcher = request.getRequestDispatcher("billingaccount-list.jsp");
				dispatcher.forward(request, response);
			}
			else if(operation.equals("update")) {
				int id = Integer.parseInt(request.getParameter("id"));
				BillingAccount existingBillingAccount = billingaccountdao.selectBillingAccount(id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/billingaccount-form.jsp");
				request.setAttribute("billingaccounts", existingBillingAccount);
				dispatcher.forward(request, response);
			}
			else if(operation.equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				billingaccountdao.deleteBillingAccount(id);
				List<BillingAccount> billingaccounts = billingaccountdao.selectAllAccounts();
				request.setAttribute("billingaccounts", billingaccounts);
				RequestDispatcher dispatcher = request.getRequestDispatcher("billingaccount-list.jsp");
				dispatcher.forward(request, response);
			}
			else if(operation.equals("list")) {
				List<BillingAccount> billingaccounts = billingaccountdao.selectAllAccounts();
				request.setAttribute("billingaccounts", billingaccounts);
				RequestDispatcher dispatcher = request.getRequestDispatcher("billingaccount-list.jsp");
				dispatcher.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("From Billing Account Servlet");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
