package com.kartik.codility;

import java.util.Arrays;
import java.util.Scanner;

public class CandidateCode {
	
	public String sortingComparision(int[] a,int []b){
		Arrays.sort(a);
		Arrays.sort(b);
		  for (int i = 0; i < b.length; i++) {
			  if(a[i]>b[i]){
				  continue;
			  }else{
				  return "LOSE";
			  }
			  
		}
		return "WIN"; 
		 
	   }
	public static void main(String[] args) throws Exception{
		Scanner scanner = new Scanner(System.in);
		try {
			int T = scanner.nextInt();
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			// 1 <= T <= 10
			if (T <= 0 && T > 10) {
				return;
			}
			for (int j = 0; j < T; j++) {
				int N = scanner.nextInt();
				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
				if (N <= 0 && N > 1000) {
					return;
				} else {
					int[] a = new int[N];
					int[] b = new int[N];
					for (int k = 0; k < N; k++) {
						int strength = scanner.nextInt();
						scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
						if (strength <1 && strength > 100000) {
							return;
						} else {
							a[k] = strength;
						}
					}
					for (int k = 0; k < N; k++) {	
						int energy = scanner.nextInt();
						scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
						// 1 <= weight of each cake <= 1000
						if (energy <1 && energy > 100000) {
							return;
						} else {
							b[k] = energy;
						}
					}
					CandidateCode cc=new CandidateCode();
					System.out.println(cc.sortingComparision(b, a));
				}
			}
		}catch(Exception e){
			System.out.println();
			//https://medium.com/@codingfreak/500-data-structures-and-algorithms-practice-problems-35afe8a1e222
		}
	

	}
}
