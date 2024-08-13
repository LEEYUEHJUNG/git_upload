package com.cathay.bk.practice.nt50355.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Practice0701 {

	public static void main(String[] args) {

		String dbUrl = "jdbc:oracle:thin:@//localhost:1521/XE"; 
		String username = "student";
		String password = "student123456";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = DriverManager.getConnection(dbUrl, username, password);

			String sql = "select MANUFACTURER, TYPE, MIN_PRICE, PRICE from CARS";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Map<String, Object>> carList = new ArrayList<>();

			while (rs.next()) {
				Map<String, Object> car = new HashMap<>();
				car.put("MANUFACTURER", rs.getString("MANUFACTURER"));
				car.put("TYPE", rs.getString("TYPE"));
				car.put("MIN_PRICE", rs.getDouble("MIN_PRICE"));
				car.put("PRICE", rs.getDouble("PRICE"));
				carList.add(car);
			}

			for (Map<String, Object> car : carList) {
				System.out.println("Manufacturer: " + car.get("MANUFACTURER"));
				System.out.println("Type: " + car.get("TYPE"));
				System.out.println("Min Price: " + car.get("MIN_PRICE"));
				System.out.println("Price: " + car.get("PRICE"));
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
