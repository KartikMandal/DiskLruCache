package com.kartik.codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Candidate {
	/**
	 * 
	 * @param arr
	 * @return
	 */
	public static String sumNonAdjacent(int[] arr)
	{
		int[] sum = new int[arr.length]; // store the max sum upto particular element
		Map<Integer,Integer> path=new LinkedHashMap<>();
		sum[0] = arr[0];
		sum[1] = Math.max(arr[0],arr[1]);
		if(arr[0]>arr[1]){
		path.put(sum[0],sum[0]);
		}else{
			path.put(sum[0],sum[0]);
			path.put(sum[1],sum[1]);
		}
		for(int i=2; i<arr.length; i++)
		{
			int temp = Math.max(arr[i], arr[i] + sum[i-2]);
			sum[i] = Math.max(temp, sum[i-1]);
			if(temp>sum[i-1]){
				if(arr[i]>arr[i] + sum[i-2]){
				path.put(arr[i],arr[i]);
				}else{
					path.put(arr[i] + sum[i-2],arr[i]);
				}
			  }
		}
		
		Map<Integer, Integer> reverseSortedMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		 
		reverseSortedMap.putAll(path);
		List<Integer> list=new ArrayList<Integer>();
		int tempKey=Integer.MIN_VALUE;
		int count=0;
		 for(Map.Entry<Integer,Integer> entry:reverseSortedMap.entrySet()){
			 int key = entry.getKey();
			 int value = entry.getValue();
			 if(count==0 || key==tempKey){
			 list.add(value);
			 tempKey=key-value;
			 }
			 
			 count++;
		 }
		 String rev="";
		 for (Integer integer : list) {
			System.out.print(integer+" ");
			rev=integer+"";
		}
		 
		 return rev;
		 
		//return sum[arr.length-1];

		
		/**
		  * variation to this problem : if array is circular then
          * Find maximum sum from left to right ignoring first element.
          * Find maximum sum from right to left ignoring last element.
          * Maximum of two will be the answer. It gurantees that both first and last element
          * will be not selected together.
       */
		
	}
	/*public static void main(String[] args) 
	{
		//int[] arr = {10, 5, 3, -3, -2, 6, 10};
		//int arr[] =new int[]{4, 5, 4, 3};
		//int arr[] = new int[]{5, 5, 10, 100, 10, 5};
		//int[] arr = { 1, 2, 9, 4, 5, 0, 4, 11, 6 };
 				//int arr[] =new int[]{-1, 7, 8, -5, 4};
 				//int arr[] =new int[]{3, 2, 1, -1};
 				//int arr[] =new int[]{4, 5, 4, 3};
 				//int arr[] =new int[]{5, 10, 4, -1};
 				int arr[] =new int[]{11, 12, -2, -1};
		System.out.println("max Sum with Non Adjacent element : "+sumNonAdjacent(arr));
		
	}*/
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			int T = scanner.nextInt();
			// 1 <= T <= 10
			if (T <= 0 && T > 10) {
				return;
			}
			// System.out.println(T);
			for (int j = 0; j < T; j++) {
				int N = scanner.nextInt();
				// 1 <= M <= 1000
				if (N <= 0 && N > 10000) {
					return;
				} else {
					// System.out.printf(M+" ");
					int[] nebourgh = new int[N];
					for (int k = 0; k < N; k++) {
						int ticket = scanner.nextInt();
						if (ticket <-1000 && ticket > 1000) {
							return;
						} else {
							// System.out.printf(ticket+" ");
							nebourgh[k] = ticket;
						}
					}
					sumNonAdjacent(nebourgh);
					
				}
			}
		}catch(Exception e){
			System.out.println();
		}
	

	}
}
