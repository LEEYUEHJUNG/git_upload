package com.cathay.bk.practice.nt50355.b;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Practice07 {
	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "student",
				"student123456"); Statement stmt = conn.createStatement();) {
			// 關閉自動提交
			conn.setAutoCommit(false);
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("請選擇以下指令輸入: select、insert、update、delete");
				String command = scanner.nextLine();

				switch (command.toLowerCase()) {
				case "select":
					Map<String, String> selectData = getUserInput(scanner, "製造商", "類型");
					query(conn, selectData.get("製造商"), selectData.get("類型"));
					break;
				case "insert":
					Map<String, String> insertData = getUserInput(scanner, "製造商", "類型", "底價", "售價");
					insert(conn, insertData);
					break;
				case "update":
					Map<String, String> updateData = getUserInput(scanner, "製造商", "類型", "底價", "售價");
					update(conn, updateData);
					break;
				case "delete":
					Map<String, String> deleteData = getUserInput(scanner, "製造商", "類型");
					delete(conn, deleteData.get("製造商"), deleteData.get("類型"));
					break;
				default:
					System.out.println("無效的指令");
					break;
				}
				// 成功執行後提交交易
				conn.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, String> getUserInput(Scanner scanner, String... keys) {
		Map<String, String> inputData = new HashMap<>();
		for (String key : keys) {
			System.out.print("請輸入" + key + ": ");
			inputData.put(key, scanner.nextLine());
		}
		return inputData;
	}

	public static void query(Connection conn, String manufacturer, String type) throws SQLException {
		String sql = "select MANUFACTURER, TYPE, MIN_PRICE, PRICE from STUDENT.CARS where MANUFACTURER = ? AND TYPE = ?";
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
		String sql = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, data.get("製造商"));
			pstmt.setString(2, data.get("類型"));
			pstmt.setBigDecimal(3, new BigDecimal(data.get("底價")));
			pstmt.setBigDecimal(4, new BigDecimal(data.get("售價")));
			pstmt.executeUpdate();
			System.out.println("新增成功");
		}
	}

	public static void update(Connection conn, Map<String, String> data) throws SQLException {
		String sql = "update STUDENT.CARS set MIN_PRICE = ?, PRICE = ? where MANUFACTURER = ? and TYPE = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setBigDecimal(1, new BigDecimal(data.get("底價")));
			pstmt.setBigDecimal(2, new BigDecimal(data.get("售價")));
			pstmt.setString(3, data.get("製造商"));
			pstmt.setString(4, data.get("類型"));
			pstmt.executeUpdate();
			System.out.println("更新成功");
		}
	}

	public static void delete(Connection conn, String manufacturer, String type) throws SQLException {
		String sql = "delete from STUDENT.CARS WHERE MANUFACTURER = ? and TYPE = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);
			pstmt.executeUpdate();
			System.out.println("刪除成功");
		}
	}
}
