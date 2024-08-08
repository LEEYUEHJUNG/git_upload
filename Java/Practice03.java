package com.cathay.bk.practice.nt50355.b;

// IWork介面
interface IWork {
	void printInfo();
}

public class Practice03 {

	public static void main(String[] args) {

	}

}

// Employee類別
class Employee implements IWork {
	private String name;
	private String department;
	private int salary;

	public Employee(String name, String department, int salary) {
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	@Override
	public void printInfo() {
		System.out.print("姓名: " + name);
		System.out.println("  工作部門: " + department);
		System.out.println("月薪: " + salary);
	}

	// Getter 和 Setter 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
}

// Sales類別
class Sales extends Employee {
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

// Supervisor類別
class Supervisor extends Employee {
	private int payment;

	public Supervisor(String name, String department, int salary) {
		super(name, department, salary);
		this.payment = salary;
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("總計: " + payment);
	}

	// Getter 和 Setter 方法
	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}
}
