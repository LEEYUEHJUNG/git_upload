package com.cathay.bk.practice.nt50355.b;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Sales extends Employee {
	private BigDecimal bonus;
	private BigDecimal payment;

	public Sales(String name, String department, BigDecimal salary, BigDecimal salesAmount) {
		super(name, department, salary);
		this.bonus = salesAmount.multiply(new BigDecimal("0.05")).setScale(0,RoundingMode.HALF_UP) ;
		this.payment = salary.add(this.bonus).setScale(0,RoundingMode.HALF_UP);
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("業績獎金: " + bonus);
		System.out.println("總計: " + payment);
	}

	// Getter 和 Setter 方法
	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment.setScale(0,RoundingMode.HALF_UP);
	}
}
