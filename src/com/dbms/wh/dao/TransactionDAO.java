package com.dbms.wh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.dbms.wh.bean.BillingAccount;
import com.dbms.wh.bean.Prescription;

public class TransactionDAO {
	public static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/cagarwa3";
	public static final String user = "cagarwa3";
	public static final String password = "200234585";
	Connection connection = null;
	Statement statement = null;
	ResultSet result = null;
	
	public void testBillingTransaction(BillingAccount billingaccount) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				connection.setAutoCommit(false);
				statement = connection.createStatement();
				int installment = billingaccount.getPaid_by_person();
				System.out.println("Original Value: " + installment);
				installment /= 10;
				int paid_by_person = installment;
				for(int i = 0; i <10; i++) {
					statement.executeUpdate("UPDATE billing_accounts SET staff_id = " + billingaccount.getStaff_id() + ", checkin_id = " + billingaccount.getCheckin_id()
					+ ", paid_by_person = " + paid_by_person + ", paid_by_insurance = " + billingaccount.getPaid_by_insurance() + ", payment_info = '"
					+ billingaccount.getPayment_info() + "', payee_ssn = " + billingaccount.getPayee_ssn() + ", billing_address = '"
					+ billingaccount.getBilling_address() + "', total_charge = "+ billingaccount.getTotal_charge() +"  WHERE id = " + billingaccount.getId() + ";");
					
					if(i == 3) {
						connection.commit();
						System.out.println("Committed at: " + paid_by_person);
					}
					paid_by_person += installment;
					if(i == 9) {
						throw new Exception("Invalid Credit Card");
					}
					System.out.println("Current Amount: " + paid_by_person);
				}
//				connection.setAutoCommit(true);
//				System.out.println("Billing Account updated successfully!");
//				close(result);
//				close(statement);
//				close(connection);
			} catch (Exception e) {
				System.out.println("Exception: " + e + " \n aborting transaction. Initiating Rollback!");
				connection.rollback();
				System.out.println("Rollback done.");
				connection.setAutoCommit(true);
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
			close(result);
			close(statement);
			close(connection);
		}
	}
	
	public void testPrescriptionTransaction(Prescription prescription) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				connection.setAutoCommit(false);
				statement = connection.createStatement();
				int org = prescription.getQuantity();
				int installment = org;
				
				for(int i = 0; i <10; i++) {
					statement.executeUpdate("UPDATE prescriptions SET quantity = " + installment + " where id = "+ prescription.getId() +";");
					if(i == 3) {
						connection.commit();
						System.out.println("Committed at: " + installment);
					}
					installment++;
					if(i == 9) {
						throw new Exception("Drug Overdose");
					}
					System.out.println("Current Quantity: " + installment);
				}
			} catch (Exception e) {
				System.out.println("Exception: " + e + " \n aborting transaction. Initiating Rollback!");
				connection.rollback();
				System.out.println("Rollback done.");
				connection.setAutoCommit(true);
				close(result);
				close(statement);
				close(connection);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
			close(result);
			close(statement);
			close(connection);
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
