package com.bank.application;

public interface BankOperation {
	
	void aacountOpen(Account acc);
	Account checkBalance(Long accno);
	Account deposite(Long accno, Double amount);
	Account withdrawal(Long accno, Double amount);

}
