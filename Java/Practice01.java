package com.cathay.bk.practice.nt50355.b;

public class Practice01 {

	public static void main(String[] args) {

		// 乘數1-9
		for (int i = 1; i <= 9; i++) {
			// 被乘數2-9
			for (int j = 2; j <= 9; j++) {
				// 格式化輸出被乘數*乘數=乘積
				System.out.printf("%d*%d=%2d  ", j, i, j * i);
			}
			System.out.println();
		}
	}

}
