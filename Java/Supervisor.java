package com.cathay.bk.practice.nt50355.b;

import java.math.BigDecimal;

public class Supervisor extends Employee {
	private BigDecimal payment;

	public Supervisor(String name, String department, BigDecimal salary) {
		super(name, department, salary);
		this.payment = salary;
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("總計: " + payment);
	}

	// Getter 和 Setter 方法
	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
}
