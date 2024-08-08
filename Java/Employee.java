package com.cathay.bk.practice.nt50355.b;

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


