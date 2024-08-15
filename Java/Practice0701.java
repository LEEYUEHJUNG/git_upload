package com.cathay.bk.practice.nt50355.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Practice0701 {
	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try (Connection conn1 = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "student",
				"student123456"); Statement stmt = conn1.createStatement();) {

			String sql = "select MANUFACTURER, TYPE, MIN_PRICE, PRICE from STUDENT.CARS";
			pstmt = conn1.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("Manufacturer: " + rs.getString("MANUFACTURER"));
				System.out.println("Type: " + rs.getString("TYPE"));
				System.out.println("Min Price: " + rs.getDouble("MIN_PRICE"));
				System.out.println("Price: " + rs.getDouble("PRICE"));
				System.out.println("---------------------------");
			}

		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
