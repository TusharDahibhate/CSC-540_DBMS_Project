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
				//Setting parameters for the connection object
				connection = DriverManager.getConnection(jdbcURL, user, password);
				// Setting the auto-commit to false
				connection.setAutoCommit(false);
				// Instantiating the Statement object
				statement = connection.createStatement();
				// Getting the amount paid by person, and storing it into installment
				int installment = billingaccount.getPaid_by_person();
				// Displaying the original value
				System.out.println("Original Value: " + installment);
				// Splitting installment into 10 parts
				installment /= 10;
				// paying up the first installment
				int paid_by_person = installment;
				// loop through the number of installments
				for(int i = 0; i <10; i++) {
					// Update billing account to show updated installment payment in the column "paid by person"
					statement.executeUpdate("UPDATE billing_accounts SET staff_id = " + billingaccount.getStaff_id() + ", checkin_id = " + billingaccount.getCheckin_id()
					+ ", paid_by_person = " + paid_by_person + ", paid_by_insurance = " + billingaccount.getPaid_by_insurance() + ", payment_info = '"
					+ billingaccount.getPayment_info() + "', payee_ssn = " + billingaccount.getPayee_ssn() + ", billing_address = '"
					+ billingaccount.getBilling_address() + "', total_charge = "+ billingaccount.getTotal_charge() +"  WHERE id = " + billingaccount.getId() + ";");
					
					// Commiting after the payment of 3rd installment
					if(i == 3) {
						connection.commit();
						System.out.println("Committed at: " + paid_by_person);
					}
					// incrementing the amount paid by the person
					paid_by_person += installment;
					// Knowingly throwing exception to simulate an error, and to prove the robustness and error handling of the transaction
					if(i == 9) {
						throw new Exception("Invalid Credit Card");
					}
					System.out.println("Current Amount: " + paid_by_person);
				}
			} catch (Exception e) {
				// Displaying error message
				System.out.println("Exception: " + e + " \n aborting transaction. Initiating Rollback!");
				// Rolling back to the last committed state
				connection.rollback();
				System.out.println("Rollback done.");
				// re-setting auto-commit to true
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
				//Setting parameters for the connection object
				connection = DriverManager.getConnection(jdbcURL, user, password);
				// Setting the auto-commit to false
				connection.setAutoCommit(false);
				// Instantiating the Statement object
				statement = connection.createStatement();
				// Storing the quantity of the medications prescribed in a given prescription
				int org = prescription.getQuantity();
				// Storing the quantity in another variable
				int installment = org;
				
				for(int i = 0; i <10; i++) {
					// updating the quantity of the medication in the prescriptions table
					statement.executeUpdate("UPDATE prescriptions SET quantity = " + installment + " where id = "+ prescription.getId() +";");
					if(i == 3) {
						// commiting after the third update
						connection.commit();
						System.out.println("Committed at: " + installment);
					}
					// incrementing the value of the quantity of the medication
					installment++;
					if(i == 9) {
						// Knowingly throwing exception to simulate an error, and to prove the robustness and error handling of the transaction
						throw new Exception("Drug Overdose");
					}
					System.out.println("Current Quantity: " + installment);
				}
			} catch (Exception e) {
				// Displaying error message
				System.out.println("Exception: " + e + " \n aborting transaction. Initiating Rollback!");
				// Rolling back to the last committed state
				connection.rollback();
				System.out.println("Rollback done.");
				// re-setting auto-commit to true
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
