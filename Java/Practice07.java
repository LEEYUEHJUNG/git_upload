
package com.cathay.bk.practice.nt50355.b;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Practice07 {
    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
    
    private static final String SQL_SELECT = "select MANUFACTURER, TYPE, MIN_PRICE, PRICE from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";
    private static final String SQL_UPDATE = "update STUDENT.CARS set MIN_PRICE = ?, PRICE = ? where MANUFACTURER = ? and TYPE = ?";
    private static final String SQL_DELETE = "delete from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(JDBC_URL, "student", "student123456")) {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("請選擇以下指令輸入: select、insert、update、delete");
                String command = scanner.nextLine();

                switch (command.toLowerCase()) {
                    case "select":
                        Map<String, String> selectData = getUserInput(scanner, "製造商", "類型");
                        query(conn, selectData.get("製造商"), selectData.get("類型"));
                        break;
                    case "insert":
                        insert(conn, getUserInput(scanner, "製造商", "底價", "售價", "類型"));
                        break;
                    case "update":
                    case "delete":
                        performUpdateOrDelete(conn, scanner, command.toLowerCase());
                        break;
                    default:
                        System.out.println("無效的指令");
                        break;
                }

            } catch (Exception e) {
                // 發生錯誤時回滾交易
                conn.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insert(Connection conn, Map<String, String> userInput) {
		
	}

	private static Map<String, String> getUserInput(Scanner scanner, String... keys) {
        Map<String, String> inputData = new HashMap<>();
        for (String key : keys) {
            System.out.print("請輸入" + key + ": ");
            inputData.put(key, scanner.nextLine());
        }
        return inputData;
    }

    private static void performUpdateOrDelete(Connection conn, Scanner scanner, String command) throws SQLException {
        Map<String, String> data;
        if (command.equals("update")) {
            data = getUserInput(scanner, "製造商", "底價", "售價", "類型");
            executeUpdateOrDelete(conn, SQL_UPDATE, data);
            System.out.println("更新成功");
        } else if (command.equals("delete")) {
            data = getUserInput(scanner, "製造商", "類型");
            executeUpdateOrDelete(conn, SQL_DELETE, data);
            System.out.println("刪除成功");
        }
    }

    private static void executeUpdateOrDelete(Connection conn, String sql, Map<String, String> data) throws SQLException {
        try {
            conn.setAutoCommit(false); // 禁用自動提交
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                int index = 1;
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    if (entry.getKey().equals("底價") || entry.getKey().equals("售價")) {
                        pstmt.setBigDecimal(index++, new BigDecimal(entry.getValue()));
                    } else {
                        pstmt.setString(index++, entry.getValue());
                    }
                }
                pstmt.executeUpdate(); // 執行 SQL
                conn.commit(); // 提交交易
            } catch (SQLException e) {
                conn.rollback(); // 發生錯誤時回滾交易
                throw e;
            }
        } finally {
            conn.setAutoCommit(true); // 恢復自動提交狀態
        }
    }

    public static void query(Connection conn, String manufacturer, String type) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {
            pstmt.setString(1, manufacturer);
            pstmt.setString(2, type);
            pstmt.executeQuery();
            System.out.println("查詢成功");
        }
    }
}
