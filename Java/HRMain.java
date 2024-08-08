package com.cathay.bk.practice.nt50355.b;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HRMain {

	public static void main(String[] args) {
		List<Employee> employeeList = new ArrayList<>();

		// 添加員工
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "保代部", 38000, 6000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));

		// 顯示所有員工信息
		for (Employee employee : employeeList) {
			employee.printInfo();
			System.out.println();
		
		
		}
        // 將員工資料寫入 CSV 檔案 (Practice 04)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Admin\\Desktop\\output.csv"))) {
            writer.write("Name,Payment\n");
            for (Employee employee : employeeList) {
                writer.write(employee.getName() + "," + employee.getSalary() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
