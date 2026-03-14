package com.bank.application;

public class Account {

	private String name;
	private int age;
	private long accno;
	private double bal;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public Account(String name, int age, long accno, double bal) {
		super();
		this.name = name;
		this.age = age;
		this.accno = accno;
		this.bal = bal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getAccno() {
		return accno;
	}

	public void setAccno(long accno) {
		this.accno = accno;
	}

	public double getBal() {
		return bal;
	}

	public void setBal(double bal) {
		this.bal = bal;
	}

	
	  @Override public String toString() { return "Account [name=" + name +
	  ", age=" + age + ", accno=" + accno + ", bal=" + bal + "]"; }
	 
	
	
	
	
}
