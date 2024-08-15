package com.cathay.bk.practice.nt50355.b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class CarsProcessor {

    public static void main(String[] args) {
        String csvFile = "C:\\Users\\Admin\\Desktop\\cars.csv"; 
        String line;
        String csvSplitBy = ",";
        List<Map<String, String>> carsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String[] headers = br.readLine().split(csvSplitBy); 

            while ((line = br.readLine()) != null) {
                String[] carData = line.split(csvSplitBy);
                Map<String, String> carMap = new TreeMap<>();
                for (int i = 0; i < headers.length; i++) {
                    carMap.put(headers[i], carData[i]);
                }
                carsList.add(carMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用匿名類別進行排序
        Collections.sort(carsList, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> car1, Map<String, String> car2) {
                return car1.get("Manufacturer").compareTo(car2.get("Manufacturer"));
            }
        });

        // 按製造商進行分組和計算總計
        Map<String, BigDecimal[]> manufacturerTotals = new TreeMap<>();
        for (Map<String, String> car : carsList) {
            String manufacturer = car.get("Manufacturer");
            BigDecimal minPrice = new BigDecimal(car.get("Min.Price"));
            BigDecimal price = new BigDecimal(car.get("Price"));

            if (!manufacturerTotals.containsKey(manufacturer)) {
                manufacturerTotals.put(manufacturer, new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO});
            }
            BigDecimal[] totals = manufacturerTotals.get(manufacturer);
            totals[0] = totals[0].add(minPrice);
            totals[1] = totals[1].add(price);
        }

        // 輸出排序後並按製造商分組的數據
       
        for (Map.Entry<String, BigDecimal[]> entry : manufacturerTotals.entrySet()) {
            System.out.println("製造商: " + entry.getKey());
            System.out.println("Min.Price 總計: " + entry.getValue()[0]);
            System.out.println("Price 總計: " + entry.getValue()[1]);
            System.out.println();
        }
    }

}
