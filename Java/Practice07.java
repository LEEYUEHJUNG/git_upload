package com.cathay.bk.practice.nt50355.b;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Practice07 {

	public static void main(String[] args) {
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String dbUrl = "jdbc:oracle:thin:@//localhost:1521/XE";
		String username = "student";
		String password = "student123456";

		Connection conn = null;

		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(dbUrl, username, password);
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("請選擇以下指令輸入: select、insert、update、delete");
				String command = scanner.nextLine();

				switch (command.toLowerCase()) {
				case "select":
					System.out.print("請輸入製造商:");
					String manufacturer = scanner.nextLine();
					System.out.print("請輸入類型:");
					String type = scanner.nextLine();
					query(conn, manufacturer, type);
					break;
				case "insert":
					Map<String, String> insertData = new HashMap<>();
					System.out.print("請輸入製造商:");
					insertData.put("MANUFACTURER", scanner.nextLine());
					System.out.print("請輸入類型:");
					insertData.put("TYPE", scanner.nextLine());
					System.out.print("請輸入底價:");
					insertData.put("MIN_PRICE", scanner.nextLine());
					System.out.print("請輸入售價:");
					insertData.put("PRICE", scanner.nextLine());
					insert(conn, insertData);
					break;
				case "update":
					Map<String, String> updateData = new HashMap<>();
					System.out.print("請輸入製造商:");
					updateData.put("MANUFACTURER", scanner.nextLine());
					System.out.print("請輸入類型:");
					updateData.put("TYPE", scanner.nextLine());
					System.out.print("請輸入底價:");
					updateData.put("MIN_PRICE", scanner.nextLine());
					System.out.print("請輸入售價:");
					updateData.put("PRICE", scanner.nextLine());
					update(conn, updateData);
					break;
				case "delete":
					System.out.print("請輸入製造商:");
					manufacturer = scanner.nextLine();
					System.out.print("請輸入類型:");
					type = scanner.nextLine();
					delete(conn, manufacturer, type);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void query(Connection conn, String manufacturer, String type) throws SQLException {
		String sql = "SELECT MANUFACTURER, TYPE, MIN_PRICE, PRICE FROM CARS WHERE MANUFACTURER = ? AND TYPE = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					System.out.println("Manufacturer: " + rs.getString("MANUFACTURER"));
					System.out.println("Type: " + rs.getString("TYPE"));
					System.out.println("Min Price: " + rs.getBigDecimal("MIN_PRICE"));
					System.out.println("Price: " + rs.getBigDecimal("PRICE"));
					System.out.println("---------------------------");
				}
			}
		}
	}

	public static void insert(Connection conn, Map<String, String> data) throws SQLException {
		String sql = "INSERT INTO CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.get("MANUFACTURER"));
			pstmt.setString(2, data.get("TYPE"));
			pstmt.setBigDecimal(3, new BigDecimal(data.get("MIN_PRICE")));
			pstmt.setBigDecimal(4, new BigDecimal(data.get("PRICE")));
			System.out.println("新增成功");
		}
	}

	public static void update(Connection conn, Map<String, String> data) throws SQLException {
		String sql = "UPDATE CARS SET MIN_PRICE = ?, PRICE = ? WHERE MANUFACTURER = ? AND TYPE = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setBigDecimal(1, new BigDecimal(data.get("MIN_PRICE")));
			pstmt.setBigDecimal(2, new BigDecimal(data.get("PRICE")));
			pstmt.setString(3, data.get("MANUFACTURER"));
			pstmt.setString(4, data.get("TYPE"));
			System.out.println("新增成功");
		}
	}

	public static void delete(Connection conn, String manufacturer, String type) throws SQLException {
		String sql = "DELETE FROM CARS WHERE MANUFACTURER = ? AND TYPE = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);
			System.out.println("新增成功");
		}
	}
}
