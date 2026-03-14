package com.bank.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankOperationImpl implements BankOperation {

	List<Account> account;
	// Long accno = 1234456L;

	public BankOperationImpl() {
		account = new ArrayList<>();
	}

	@Override
	public void aacountOpen(Account acc) {
		// TODO Auto-generated method stub
		if (acc.getBal() < 500.00) {
			new Exception("Initial Deposite should be more than 500.00");
		} else {
			try {
				long newAccNo = 0;

				Connection connection = JdbcUtility.getConnection();

				// 2. Fetch max account number
				String maxQuery = "SELECT NVL(MAX(accno), 100000) FROM Account";
				PreparedStatement maxStmt = connection.prepareStatement(maxQuery);
				ResultSet rs = maxStmt.executeQuery();

				if (rs.next()) {
					newAccNo = rs.getLong(1) + 1; // increment max by 1
				}

				PreparedStatement stmt = connection
						.prepareStatement("INSERT INTO Account(name, age, accno, bal) VALUES (?, ?, ?, ?)");
				stmt.setString(1, acc.getName());
				stmt.setInt(2, acc.getAge());
				stmt.setLong(3, newAccNo);
				stmt.setDouble(4, acc.getBal());

				int rowsInserted = stmt.executeUpdate();
				if (rowsInserted > 0) {
					acc.setAccno(newAccNo);
					System.out.println("Account created sucessfully. " + acc);
				}

				connection.close();
				rs.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Exception while creating a Account : " + e.getMessage());
			}
		}

	}

	@Override
	public Account checkBalance(Long accno) {
		// TODO Auto-generated method stub

		try {

			Connection connection = JdbcUtility.getConnection();

			//PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Account WHERE accno = ?");
			//stmt.setLong(1, accno); // use setInt if accno is int
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Account WHERE accno = "+accno);

			while (rs.next()) {
				String name = rs.getString("name");
				int age = rs.getInt("age");
				long accountNo = rs.getLong("accno");
				double balance = rs.getDouble("bal");

				System.out.printf("Account holder Name: %s, Age: %d, AccNo: %d, Balance: %.2f\n", name, age, accountNo, balance);
			}
			connection.close();
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception while Checking Account Balance : " + e.getMessage());
		}
		return null;
	}
	//get account details based on account number
	//if no record found - error("Account details not found);
	//if account details found then add amount and update remaining balance.
    //save final amount in db.
	@Override
	public Account deposite(Long accno, Double amount) {     
		String name = "";
		int age =0;
		long accountNo;
		double balance =0.0;
		Connection connection =null;
		try {
			connection = JdbcUtility.getConnection();
	        Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Account WHERE accno = "+accno);
			while (rs.next()) {
				name = rs.getString("name");
				age = rs.getInt("age");
				accountNo = rs.getLong("accno");
				balance = rs.getDouble("bal");
				System.out.printf("Account holder Name: %s, Age: %d, AccNo: %d, Balance: %.2f\n", name, age, accountNo, balance);
				}
			rs.close();
			connection.close();
			}
		catch (SQLException e) {
			e.printStackTrace();
			}
		
		double updatedBal = balance + amount;
		try {
			connection = JdbcUtility.getConnection();
	        String updateSql = "UPDATE Account SET bal = ? WHERE accno = ?";
	        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
	        updateStmt.setDouble(1, updatedBal);
	        updateStmt.setLong(2, accno);
	        int rows = updateStmt.executeUpdate();
	        if (rows > 0) {
	        	System.out.printf(" ?%.2f withdrawn from Account %d. New balance: ?%.2f%n", amount, accno, updatedBal);
	        	}
	        else {
	        	System.out.println("Withdrawal failed for Account: " + accno);
	        	}
	        Account acc = new Account();
	        acc.setAccno(accno);
	        acc.setName(name);
	        acc.setAge(age);
	        acc.setBal(updatedBal);
	        connection.close();
	        return acc;
	        } 
		catch (SQLException e) {
			System.out.println("Exception during withdrawal: " + e.getMessage());
	        return null;
	        }
		}
        
	//get account details based on account number
	//if no record found - error("Account details not found);
	//if account details found then withdraw amount and update remaining balance.
	//save final amount in db.
	@Override
	public Account withdrawal(Long accno, Double amount) {      
		
		//corrected previous code from chatgpt 
		String name = "";
		int age =0;
		long accountNo;
		double balance =0.0;
		Connection connection =null;
		
		try {
        	
	        
	        connection = JdbcUtility.getConnection();
	        Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Account WHERE accno = "+accno);

			while (rs.next()) {
				name = rs.getString("name");
				age = rs.getInt("age");
				accountNo = rs.getLong("accno");
				balance = rs.getDouble("bal");

				System.out.printf("Account holder Name: %s, Age: %d, AccNo: %d, Balance: %.2f\n", name, age, accountNo, balance);
			}
			
			rs.close();
			connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        

	        // 2. Check for sufficient balance
	        if (balance < amount) {
	            //System.out.println(" Insufficient balance. Available: ?" + balance + ", Tried to withdraw: ?" + amount);
	            return null;
	        }

	        // 3. Deduct amount
	        
	        double updatedBal = balance - amount;
           try {
	        // 4. Update balance in DB
        	connection = JdbcUtility.getConnection();
	        String updateSql = "UPDATE Account SET bal = ? WHERE accno = ?";
	        PreparedStatement updateStmt = connection.prepareStatement(updateSql);
	        updateStmt.setDouble(1, updatedBal);
	        updateStmt.setLong(2, accno);

	        int rows = updateStmt.executeUpdate();
	        if (rows > 0) {
	            System.out.printf(" ?%.2f withdrawn from Account %d. New balance: ?%.2f%n", amount, accno, updatedBal);
	        } else {
	            System.out.println("Withdrawal failed for Account: " + accno);
	        }

	        // 5. Return updated account
	        Account acc = new Account();
	        acc.setAccno(accno);
	        acc.setName(name);
	        acc.setAge(age);
	        acc.setBal(updatedBal);
	        
			connection.close();

	        return acc;

	    } catch (SQLException e) {
	        System.out.println("Exception during withdrawal: " + e.getMessage());
	        return null;
	    }
	}
}

