package com.test.demo;
/*
 * Replace every matrix element with maximum of GCD of row or column
Given a matrix of n rows and m columns. The task is to replace each matrix element with Greatest Common Divisor of its row or column, whichever is maximum. That is, for each element (i, j) replace it from GCD of i’th row or GCD of j’th row, whichever is greater.

Examples:

Input : mat[3][4] = {1, 2, 3, 3,
                     4, 5, 6, 6
                     7, 8, 9, 9}  
Output :  1 1 3 3
          1 1 3 3
          1 1 3 3
For index (0,2), GCD of row 0 is 1, GCD of row 2 is 3.
So replace index (0,2) with 3 (3>1). 
 * 
 */
public class Demo {
	// returning the greatest common divisor of two number
	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	// Finding GCD of each row and column and replacing
	// with each element with maximum of GCD of row or
	// column.
	static void replacematrix(int mat[][], int n, int m) {

		int rgcd[] = new int[n], cgcd[] = new int [m];
		// Calculating GCD of each row and each column in
		// O(mn) and store in arrays.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				rgcd[i] = gcd(rgcd[i], mat[i][j]);
				cgcd[j] = gcd(cgcd[j], mat[i][j]);
			}
		}

		// Replacing matrix element
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				mat[i][j] = max(rgcd[i], cgcd[j]);
				System.out.print(mat[i][j]);
			}
			System.out.println();
		}
	}

	static int max(int i, int j) {
		if (i >= j)
			return i;
		else
			return j;
	}

	public static void main(String[] args) {
		int R = 3, C = 4;
		int m[][] = { { 1, 2, 3, 3 }, { 4, 5, 6, 6 }, { 7, 8, 9, 9 } };
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(m[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------------");
		replacematrix(m, R, C);

	}
}
