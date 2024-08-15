package com.cathay.bk.practice.nt50355.b;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Practice05 {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("請輸入1到12之間的整數表示月份: ");
			int month = scanner.nextInt();

			// 使用java.time.LocalDate
			LocalDate now = LocalDate.now();
			int year = now.getYear();

			System.out.println("        " + year + " 年 " + month + " 月");
			printCalendarUsingLocalDate(year, month);

		}
	}

//	private static void printCalendarUsingLocalDate(int year, int month) {
//		LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
//		DayOfWeek firstDayOfWeek = firstDayOfMonth.getDayOfWeek();
//		int daysInMonth = firstDayOfMonth.lengthOfMonth();
	private static void printCalendarUsingLocalDate(int year, int month) {
		int firstDayOfWeek = LocalDate.of(year, month, 1).getDayOfWeek().getValue();
		int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

		System.out.println("---------------------------");
		System.out.println("日   一   二  三  四   五   六");
		System.out.println("===========================");

		// 打印月曆的第一行，根據星期幾來決定縮進空格
		int value = firstDayOfWeek;
		if (value != 7) { // LocalDate中星期天是7
			for (int i = 1; i <= value; i++) {
				System.out.print("    ");
			}
		}

		// 打印月曆的日期
		for (int day = 1; day <= daysInMonth; day++) {
			System.out.printf("%2d  ", day);
			if ((day + value) % 7 == 0 || day == daysInMonth) {
				System.out.println();
			}
		}
	}

}
