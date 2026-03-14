package com.bank.application;

import java.util.Scanner;

public class TestBankingOperation {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BankOperationImpl bankOperationImpl = null;
		Scanner sc = null;
		bankOperationImpl = new BankOperationImpl();
		sc = new Scanner(System.in);

		while (true) {
			System.out.println("For Account opening press 1");
			System.out.println("for Deposite press 2");
			System.out.println("for withdrawal press 3");
			System.out.println("for checking balance press 4");
			System.out.println("Enter your input");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				System.out.println("Please enter account details");
				System.out.print("Enter your Name - ");
				String name = sc.next();
				System.out.print("Enter your Age - ");
				int age = sc.nextInt();
				System.out.print("Enter Initial Balance - ");
				double ba1 = sc.nextDouble();

				Account acc = new Account();
				acc.setName(name);
				acc.setAge(age);
				acc.setBal(ba1);
				bankOperationImpl.aacountOpen(acc);
				break;
			case 2:
				System.out.println("Enter the Account number");
				long accno = sc.nextInt();
				System.out.println("Enter the Deposite Amount");
				double da = sc.nextDouble();

				bankOperationImpl.deposite(accno, da);
				break;

			case 3:
				System.out.println("Enter the Account number");
				long accno2 = sc.nextInt();
				System.out.println("Enter the amt to Withdraw");
				double wa = sc.nextDouble();

				bankOperationImpl.withdrawal(accno2, wa);
				break;

			case 4:
				System.out.println("Enter your Account number");
				Long accno1 = sc.nextLong();
				bankOperationImpl.checkBalance(accno1);
				break;
			}

		}

	}

}
