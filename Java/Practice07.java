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
						insert(conn, getUserInput(scanner, "製造商", "底價", "售價"));
						break;
					case "update":
						Map<String, String> updateData = getUserInput(scanner, "製造商", "底價", "售價", "類型");
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

			} catch (Exception e) {
				// 發生錯誤時回滾交易
				conn.rollback();
				e.printStackTrace();
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
			pstmt.executeQuery();
		}
	}

	public static void insert(Connection conn, Map<String, String> data) throws SQLException {
		String sql = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";
		try {
			conn.setAutoCommit(false); // 禁用自動提交
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, data.get("製造商"));
				pstmt.setString(2, data.get("類型"));
				pstmt.setBigDecimal(3, new BigDecimal(data.get("底價")));
				pstmt.setBigDecimal(4, new BigDecimal(data.get("售價")));
				pstmt.executeUpdate(); // 執行 SQL
				conn.commit(); // 提交交易
				System.out.println("新增成功");
			} catch (SQLException e) {
				conn.rollback(); // 發生錯誤時回滾交易
				throw e;
			}
		} finally {
			conn.setAutoCommit(true); // 恢復自動提交狀態
		}
	}

	public static void update(Connection conn, Map<String, String> data) throws SQLException {
		String sql = "update STUDENT.CARS set MIN_PRICE = ?, PRICE = ? where MANUFACTURER = ? and TYPE = ?";
		try {
			conn.setAutoCommit(false); // 禁用自動提交
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setBigDecimal(1, new BigDecimal(data.get("底價")));
				pstmt.setBigDecimal(2, new BigDecimal(data.get("售價")));
				pstmt.setString(3, data.get("製造商"));
				pstmt.setString(4, data.get("類型"));
				pstmt.executeUpdate(); // 執行 SQL
				conn.commit(); // 提交交易
				System.out.println("更新成功");
			} catch (SQLException e) {
				conn.rollback(); // 發生錯誤時回滾交易
				throw e;
			}
		} finally {
			conn.setAutoCommit(true); // 恢復自動提交狀態
		}
	}

	public static void delete(Connection conn, String manufacturer, String type) throws SQLException {
		String sql = "delete from STUDENT.CARS WHERE MANUFACTURER = ? and TYPE = ?";
		try {
			conn.setAutoCommit(false); // 禁用自動提交
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, manufacturer);
				pstmt.setString(2, type);
				pstmt.executeUpdate(); // 執行 SQL
				conn.commit(); // 提交交易
				System.out.println("刪除成功");
			} catch (SQLException e) {
				conn.rollback(); // 發生錯誤時回滾交易
				throw e;
			}
		} finally {
			conn.setAutoCommit(true); // 恢復自動提交狀態
		}
	}
}
