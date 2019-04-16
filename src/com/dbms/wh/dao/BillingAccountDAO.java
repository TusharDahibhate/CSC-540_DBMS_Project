package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dbms.wh.bean.BillingAccount;

public class BillingAccountDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	public void createBillingAccount(BillingAccount billingaccount) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO billing_accounts (staff_id, checkin_id, paid_by_person, paid_by_insurance, payment_info, payee_ssn, billing_address, total_charge) VALUES"
						+ "(" + billingaccount.getStaff_id() + "," + billingaccount.getCheckin_id() + "," + billingaccount.getPaid_by_person() + ","
						+ billingaccount.getPaid_by_insurance() + ",'" + billingaccount.getPayment_info() + "','" + billingaccount.getPayee_ssn() + "','"
						+ billingaccount.getBilling_address() + "',"+ billingaccount.getTotal_charge() +")");
				System.out.println("New Billing Account added successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}
	
	public BillingAccount selectBillingAccount(int id) {
		BillingAccount billingaccount = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery("SELECT id, staff_id, checkin_id, paid_by_person, paid_by_insurance, payment_info, payee_ssn, billing_address, total_charge FROM billing_accounts WHERE id = "+id+"");
				while (rs.next()) {
					int staff_id = rs.getInt("staff_id");
					int checkin_id = rs.getInt("checkin_id");
					int paid_by_person = rs.getInt("paid_by_person");
					int paid_by_insurance = rs.getInt("paid_by_insurance");
					String payment_info = rs.getString("payment_info");
					String payee_ssn = rs.getString("payee_ssn");
					String billing_address = rs.getString("billing_address");
					int total_charge = rs.getInt("total_charge");
					billingaccount = new BillingAccount(id, staff_id, checkin_id, paid_by_person, paid_by_insurance, payment_info, payee_ssn, billing_address, total_charge);
				}
				
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
		return billingaccount;
	}
	
	public void updateBillingAccount(BillingAccount billingaccount) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("UPDATE billing_accounts SET staff_id = " + billingaccount.getStaff_id() + ", checkin_id = " + billingaccount.getCheckin_id()
						+ ", paid_by_person = " + billingaccount.getPaid_by_person() + ", paid_by_insurance = " + billingaccount.getPaid_by_insurance() + ", payment_info = '"
						+ billingaccount.getPayment_info() + "', payee_ssn = '" + billingaccount.getPayee_ssn() + "', billing_address = '"
						+ billingaccount.getBilling_address() + "', total_charge = "+ billingaccount.getTotal_charge() +"  WHERE id = " + billingaccount.getId() + ";");
				System.out.println("Billing Account updated successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}
	
	public List<BillingAccount> selectAllAccounts() {

		List<BillingAccount> billingaccounts = new ArrayList<>();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, user, password);
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT id, staff_id, checkin_id, paid_by_person, paid_by_insurance, payment_info, payee_ssn, billing_address, total_charge FROM billing_accounts");

			while (rs.next()) {
				int id = rs.getInt("id");
				int staff_id = rs.getInt("staff_id");
				int checkin_id = rs.getInt("checkin_id");
				int paid_by_person = rs.getInt("paid_by_person");
				int paid_by_insurance = rs.getInt("paid_by_insurance");
				String payment_info = rs.getString("payment_info");
				String payee_ssn = rs.getString("payee_ssn");
				String billing_address = rs.getString("billing_address");
				int total_charge = rs.getInt("total_charge");
				billingaccounts.add(new BillingAccount(id, staff_id, checkin_id, paid_by_person, paid_by_insurance, payment_info, payee_ssn, billing_address, total_charge));
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Exception occured at Billing Account DAO select all accounts");
		}
		return billingaccounts;
	}
	
	public void deleteBillingAccount(int billingaccount_id) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				statement = connection.createStatement();
				statement.executeUpdate("DELETE from billing_accounts where id = " + billingaccount_id + ";");
				System.out.println("Billing Account deleted successfully!");
			} finally {
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
		}
	}
	
	static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Throwable whatever) {
			}
		}
	}

	static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (Throwable whatever) {
			}
		}
	}

	static void close(ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (Throwable whatever) {
			}
		}
	}
}
