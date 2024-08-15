package com.cathay.bk.practice.nt50355.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


public class Practice06 {
	

    private static final String FORMAT = "%-15s %-10s %10s %15s";

	
	public static void main(String[] args) {

		String inputFile = "C:\\Users\\Admin\\Desktop\\cars.csv";
		String outputFile = "C:\\Users\\Admin\\Desktop\\cars2.csv";

		// 用於存儲 CSV 內容的清單，其中每個元素是包含欄位名稱及其對應值的 Map
		String[] outputHeaders = { "Manufacturer", "TYPE", "Min.PRICE", "Price" };
		List<Map<String, Object>> carsList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

			String headerLine = br.readLine();
			String[] headers = headerLine.split(",");

			String line;

			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				Map<String, Object> carData = new LinkedHashMap<>();

				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					if (isNumeric(value)) {
						carData.put(headers[i], new BigDecimal(value));
					} else {
						carData.put(headers[i], value);
					}
				}
				// 將處理好的資料加入清單
				carsList.add(carData);
			}

		} catch (IOException e) {

			e.printStackTrace();
		}

		// 將清單根據 "Price" 欄位進行排序，從高到低
		 Collections.sort(carsList, new Comparator<Map<String, Object>>() {
	            @Override
	            public int compare(Map<String, Object> car1, Map<String, Object> car2) {
	                return ((BigDecimal) car2.get("Price")).compareTo((BigDecimal) car1.get("Price"));
	            }
	        });

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

			bw.write(String.join(",", outputHeaders));
			bw.newLine();

			for (Map<String, Object> car : carsList) {
				List<String> values = new ArrayList<>();
				for (String header : car.keySet()) {
					Object value = car.get(header);
					if (value instanceof BigDecimal) {
						values.add(((BigDecimal) value).toPlainString());
					} else {
						values.add(value.toString());
					}
				}

				bw.write(String.join(",", values));
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 將清單根據 "Manufacturer" 欄位進行排序，並列印在螢幕上
		Collections.sort(carsList, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> car1, Map<String, Object> car2) {
				String manufacturer1 = (String) car1.get("Manufacturer");
				String manufacturer2 = (String) car2.get("Manufacturer");

				return manufacturer1.compareTo(manufacturer2);
			}
		});

		System.out.println(String.format(FORMAT, "Manufacturer", "TYPE", "Min.Price", "Price"));

		// 重置小計
		String lastManufacturer = "";
		BigDecimal totalMinPrice = BigDecimal.ZERO;
		BigDecimal totalPrice = BigDecimal.ZERO;
		BigDecimal grandTotalMinPrice = BigDecimal.ZERO;
		BigDecimal grandTotalPrice = BigDecimal.ZERO;

		for (Map<String, Object> car : carsList) {
			String manufacturer = (String) car.get("Manufacturer");
			BigDecimal minPrice = (BigDecimal) car.get("Min.Price");
			BigDecimal price = (BigDecimal) car.get("Price");

			// 如果遇到新製造商，打印上個製造商的小計
			if (!manufacturer.equals(lastManufacturer) && !lastManufacturer.isEmpty()) {
				System.out.println(String.format(FORMAT, "小計", "", totalMinPrice, totalPrice));

				// 重置小計
				totalMinPrice = BigDecimal.ZERO;
				totalPrice = BigDecimal.ZERO;
			}

			// 打印當前車輛的資料
			System.out.println(String.format(FORMAT, car.get("Manufacturer"), car.get("Type"), car.get("Min.Price"),
					car.get("Price")));

			// 累積小計
			totalMinPrice = totalMinPrice.add(minPrice);
			totalPrice = totalPrice.add(price);
			grandTotalMinPrice = grandTotalMinPrice.add(minPrice);
			grandTotalPrice = grandTotalPrice.add(price);
			lastManufacturer = manufacturer;
		}

		// 打印最後一個製造商的小計
//		StringUtils.isBlank(lastManufacturer);
		if (!lastManufacturer.isEmpty()) {
			System.out.println(String.format(FORMAT, "小計", "", totalMinPrice, totalPrice));
		}
		// 最後打印總計
		System.out.println(String.format(FORMAT, "合計", "", grandTotalMinPrice, grandTotalPrice));
	}

	// 判斷一個字串是否為數字，適用於 BigDecimal 的轉換
	private static boolean isNumeric(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		try {
			new BigDecimal(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
