package com.cathay.bk.practice.nt50355.b;

public class Sales extends Employee {
	private int bonus;
	private int payment;

	public Sales(String name, String department, int salary, int salesAmount) {
		super(name, department, salary);
		this.bonus = (int) (salesAmount * 0.05);
		this.payment = salary + this.bonus;
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("業績獎金: " + bonus);
		System.out.println("總計: " + payment);
	}

	// Getter 和 Setter 方法
	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}
}

