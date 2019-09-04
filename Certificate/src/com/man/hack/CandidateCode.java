package com.man.hack;

import java.util.Arrays;
import java.util.Scanner;

public class CandidateCode {
	
	
	

	public static void main(String[] args) throws Exception{
		Scanner scanner = new Scanner(System.in);
		try {
			int T = scanner.nextInt();
			// 1 <= T <= 10
			if (T <= 0 && T > 5) {
				return;
			}
			// System.out.println(T);
			for (int j = 0; j < T; j++) {
				int r=0,c=0;
				for (int m = 0; m < 2; m++) {
					if (m == 0) {
						r = scanner.nextInt();
						if (r <= 0 && r > 100000) {
							return;
						}
					}
					if (m == 1) {
						c = scanner.nextInt();
						if (c <= 0 && c > 100000) {
							return;
						}
					}
				}
				int[] boxElement = null;
				int[] boxElement2 =null;
				
					if (r>0  && r <= 100000) {
						 boxElement = new int[r];
						for (int k = 0; k < r; k++) {
							int element = scanner.nextInt();
							if (element < 0 && element > 1000000) {
								return;
							} else {
								boxElement[k] = element;
							}
						}
					}
					if (c>0  && c <= 100000) {
						boxElement2 = new int[c];
						for (int k = 0; k < c; k++) {
							int element = scanner.nextInt();
							if (element < 0 && element > 1000000) {
								return;
							} else {
								boxElement2[k] = element;
							}
						}
						
					}
				}
				
			//int []boxElement ={121,23,3,333,4};
			//int []boxElement ={32, 42, 52, 62, 72, 82, 92};
			//int []boxElement ={3,5,7,2};
			//int []boxElement={14, 12, 23, 45, 39};
			//long data=maxSum(boxElement);
			//System.out.println(data);
		}catch(Exception e){
			System.out.println();
		}
	

	}

}
