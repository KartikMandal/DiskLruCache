package com.code.hack;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			int T = scanner.nextInt();
			// 1 <= T <= 100
			if (T <= 0 && T > 100) {
				return;
			}
			// System.out.println(T);
			for (int j = 0; j < T; j++) {
				int N = scanner.nextInt();
				// 1 <= n <= 10000
				if (N <= 0 && N > 10000) {
					return;
				} else {
					// System.out.printf(M+" ");
					int[] first = new int[N];
					int[] second=new int[N];
					for (int k = 0; k < N; k++) {
						int fisrt = scanner.nextInt();
						//0 <= ar[i] <= 1000
						if (fisrt <0 && fisrt > 1000) {
							return;
						} else {
							first[k] = fisrt;
						}
					}
					for (int k = 0; k < N; k++) {
						int sec = scanner.nextInt();
						//0 <= ar[i] <= 1000
						if (sec <0 && sec > 1000) {
							return;
						} else {
							second[k] = sec;
						}
					}
					Test s=new Test();
					s.arrayMatching(first,second);
				}
			}
		}catch(Exception e){
			System.out.println();
		}
	
		/*int a[] ={1, 4, 0, 2, 5};
		int b[] ={2, 0, 5, 1, 4};
		Test s=new Test();
		s.arrayMatching(a,b);*/
	}
	
	public void arrayMatching(int[]first,int []second){
		for (int i=0;i<first.length;i++) {
			for (int j=0;j<second.length;j++) {
				if(first[i] == second[j]){
					first[i]=-1;
					second[j]=-1;
					break;
				}
			}
		}
		//Arrays.toString(first);
		int a = 0, b = 0;
		int countF=0,countS=0;
		for (int i=0;i<first.length;i++) {
			if(first[i]>-1){
				a=first[i];
				countF++;
			}
		}
		for (int j=0;j<second.length;j++) {
			if(second[j]>-1){
				b=second[j];
				countS++;
			}
		}
		//System.out.println(countF+" "+ countS);
		if(countF == 0 && countS == 0){
			System.out.println("Yes");
		}else if(countF==1 && countS==1){
			if(a>b){
				System.out.println(a-b+" "+2);
			}else{
				System.out.println(b-a+" "+1);
			}
		}else{
			System.out.println("No");
		}
	}
	
}