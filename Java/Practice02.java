package com.cathay.bk.practice.nt50355.b;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Practice02 {

	public static void main(String[] args) {

		Random rand = new Random();
		HashSet<Integer> set = new HashSet<>();

		// 生成隨機數字，直到集合數字為6
		while (set.size() < 6) {
			int i = rand.nextInt(49) + 1;
			set.add(i);
		}

		// 顯示排序前結果
		System.out.print("排序前: ");
		printNumbers(set);

		// 將數字排序,顯示排序後結果
		Set<Integer> sortedList = new TreeSet<>(set);
		System.out.print("排序後: ");
		printNumbers(sortedList);
	}

	// 打印數字
	private static void printNumbers(Iterable<Integer> numbers) {
		for (Integer number : numbers) {
			System.out.print(number + " ");
		}
		System.out.println();
	}
}
