package com.cathay.bk.practice.nt50355.b;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HRMain {

    public static void main(String[] args) {
        List<Employee> employeeList = new ArrayList<>();

        // 添加員工
        employeeList.add(new Sales("張志城", "信用卡部", new BigDecimal("35000"), new BigDecimal("6000")));
        employeeList.add(new Sales("林大鈞", "保代部", new BigDecimal("38000"), new BigDecimal("6000")));
        employeeList.add(new Supervisor("李中白", "資訊部", new BigDecimal("65000")));
        employeeList.add(new Supervisor("林小中", "理財部", new BigDecimal("80000")));

        // 排序，先排Supervisor，再排Sales
        employeeList.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                // 如果 e1 是 Supervisor 且 e2 不是 Supervisor，那麼 e1 應該排在 e2 前面
                if (e1 instanceof Supervisor && !(e2 instanceof Supervisor)) {
                    return -1;
                }
                // 如果 e2 是 Supervisor 且 e1 不是 Supervisor，那麼 e2 應該排在 e1 前面
                if (e2 instanceof Supervisor && !(e1 instanceof Supervisor)) {
                    return 1;
                }
                // 如果兩者類型相同，保持原順序
                return 0;
            }
        });

        // 顯示所有員工信息
        for (Employee employee : employeeList) {
            employee.printInfo();
            System.out.println();
        }

        // 將員工資料寫入 CSV 檔案
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Admin\\Desktop\\output.csv"))) {

            for (Employee employee : employeeList) {
                if (employee instanceof Sales) {
                    writer.write(employee.getName() + "," + ((Sales) employee).getPayment() + "\n");
                } else if (employee instanceof Supervisor) {
                    writer.write(employee.getName() + "," + ((Supervisor) employee).getPayment() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
