package com.kartik.org;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
public class So {
	/*public static void main(String[] args) {
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
					OnlyPathWithMaxSumSubseqwithNonContigious(nebourgh);
					
				}
			}
		}catch(Exception e){
			System.out.println();
		}
	

	}*/
	
	public static int ppp(int[] data) {
		String ret="";
		List<String> aa=new LinkedList<>();
	    int n = data.length;
	    int max = 0;
	    for (int i = 0; i < 1; i++) {
	      int[] circularData = new int[n];
	      for (int j = 0; j < n; j++) {
	        circularData[j] = data[(i+j) % n];
	      }
	      max = Math.max(maxSumInSubsequence(circularData,aa), max);
	    }
	    Collections.sort(aa, new Comparator<Object>() {
            public int compare(Object a1, Object a2) {
            	String p1=(String) a1;
        	    String[] p11=p1.split("|");
        	    String p2=(String) a2;
        	    String[] p22=p2.split("|");
                int first =p11[0].compareTo(p22[0]);
                int sec=p11[2].compareTo(p22[2]);
                if(sec == 0){
                	if(first==0){
                		 return first;
                	}
                   return sec;
                }
				return sec;
            }
        });
	    // find the max of all subsequences
	    String a=aa.get(0);
	    String[] aaa=a.split("|");
	    ret=aaa[0]+""+aaa[2];
	    return max;
	  }
	
	public static int maxSumInSubsequence(int[] data,List<String> aa) { 
		
		
	    if (data == null) return 0;
	    int n = data.length;
	    if (n <= 2) return 0;

	    // maxSum[i] == the maximum sum of subsequences of data[0 .. i] that include data[i]  
	    int[] maxSum = new int[n];  
	    for (int i=0; i<n; ++i) {  
	      maxSum[i] = data[i];  
	      // maxSum[i-1] includes data[i-1] and thus cannot include data[i]  
	      for (int j=0; j<i-1; ++j) {  
	        maxSum[i] = Math.max(data[i] + maxSum[j], maxSum[i]);
	        if(!aa.contains((data[i]+"|"+ maxSum[j]).toString()))
	        aa.add(data[i]+"|"+ maxSum[j]);
	      }  
	    }  
	   
	    int max = 0;  
	    for (int i=0; i<n; ++i) {  
	    max = Math.max(max, maxSum[i]);  
	  }

	  return max;  
	  }

	/*public static void ppp(int []A){
	       int n=A.length;
	       int max1 = A[0];
	       if(n<3)
	         {
	            if(n==1){
	              System.out.println(A[0]);
	            }else{
	              System.out.println(Integer.max(A[0],A[1]));
	            }
	         }
	 
	       int max2 = A[1];
	       int max3 = A[0]+A[2];
	       for(int j=3;j<n;j++)
	         {
	            int temp = A[j]+Integer.max(max1,max2);
	            max1=max2;
	            max2=max3;
	            max3=temp;
	         }
	       System.out.println(Integer.max(max2,max3));
	    }*/
	
	
	// DP solution to calculate maximum sum in the given array with
	// no adjacent elements considered (Function uses extra space)
	public static void OnlyPathWithMaxSumSubseqwithNonContigious(int[] A) {
		int n = A.length;
		List<Integer> list=new LinkedList<Integer>();
		// base case
		if (n == 1) {
			if(A[0]>0)
			list.add(A[0]);
		}
		// create an auxiliary array to store solution of sub-problems
		int[] lookup = new int[n];
		// lookup[i] stores the maximum sum possible till index i
		// trivial case
		lookup[0] = A[0];
		lookup[1] = Integer.max(A[0], A[1]);
		
		if(A[0]>0)
		list.add(A[0]);
		if(A[1]>0)
		list.add(A[1]);
		// traverse array from index 2
		for (int i = 2; i < n; i++) {
			// lookup[i] stores the maximum sum we get by
			// 1. excluding current element & take maximum sum till index i-1
			// 2. including current element A[i] and take maximum sum
			// till index i-2
			if(lookup[i - 1]<lookup[i - 2] + A[i]){
				if(A[i]>0)
				list.add(A[i]);
			}
			lookup[i] = Integer.max(lookup[i - 1], lookup[i - 2] + A[i]);
			
			// if current element is more than max sum till current element
			if(lookup[i]< A[i] && A[i]>0){
					list.add(A[i]);
			}
			lookup[i] = Integer.max(lookup[i], A[i]);
		}
		
		int[] aa = list.stream()
				.mapToInt(Integer::intValue)
				.toArray();
		printAllSubsets(aa, aa.length, lookup[n-1]);
		
	}
	
	
	// dp[i][j] is going to store true if sum j is 
    // possible with array elements from 0 to i. 
    static boolean[][] dp; 
       
    static void display(LinkedList<Integer> v) 
    { 
    	for (Integer integer : v) {
    		System.out.print(integer+"");
		}
       System.out.println(); 
    } 
       
    // A recursive function to print all subsets with the 
    // help of dp[][]. Vector p[] stores current subset. 
    static void printSubsetsRec(int arr[], int i, int sum,  
                                         LinkedList<Integer> p) 
    { 
        // If we reached end and sum is non-zero. We print 
        // p[] only if arr[0] is equal to sun OR dp[0][sum] 
        // is true. 
        if (i == 0 && sum != 0 && dp[0][sum]) 
        { 
            p.add(arr[i]); 
            display(p); 
            p.clear();
            return; 
        } 
       
        // If sum becomes 0 
        if (i == 0 && sum == 0) 
        { 
            display(p); 
            p.clear(); 
            return; 
        } 
       
        // If given sum can be achieved after ignoring 
        // current element. 
        if (dp[i-1][sum]) 
        { 
            // Create a new vector to store path 
        	LinkedList<Integer> b = new LinkedList<>(); 
            b.addAll(p); 
            printSubsetsRec(arr, i-1, sum, b); 
        } 
       
        // If given sum can be achieved after considering 
        // current element. 
        if (sum >= arr[i] && dp[i-1][sum-arr[i]]) 
        { 
            p.add(arr[i]); 
            printSubsetsRec(arr, i-1, sum-arr[i], p); 
        } 
    } 
       
    // Prints all subsets of arr[0..n-1] with sum 0. 
    static void printAllSubsets(int arr[], int n, int sum) 
    { 
        if (n == 0 || sum < 0) 
           return; 
       
        // Sum 0 can always be achieved with 0 elements 
        dp = new boolean[n][sum + 1]; 
        for (int i=0; i<n; ++i) 
        { 
            dp[i][0] = true;   
        } 
       
        // Sum arr[0] can be achieved with single element 
        if (arr[0] <= sum) 
           dp[0][arr[0]] = true; 
       
        // Fill rest of the entries in dp[][] 
        for (int i = 1; i < n; ++i) 
            for (int j = 0; j < sum + 1; ++j) 
                dp[i][j] = (arr[i] <= j) ? (dp[i-1][j] || 
                                           dp[i-1][j-arr[i]]) 
                                         : dp[i - 1][j]; 
        if (dp[n-1][sum] == false) 
        { 
            System.out.println("There are no subsets with" +  
                                                  " sum "+ sum); 
            return; 
        } 
       
        // Now recursively traverse dp[][] to find all 
        // paths from dp[n-1][sum] 
        LinkedList<Integer> p = new LinkedList<>(); 
        printSubsetsRec(arr, n-1, sum, p); 
    } 
    
    
 // Driver program to test above functions
 	public static void main(String[] args) {
 		/*int arr[] = new int[] { 5, 5, 10, 100, 10, 5 };
 		pathWithMaxSumSubseqwithNonContigious(arr);
 		int[] path = p.path;
 		int sum = p.sum;
 		System.out.println("Path of Non contigious " + Arrays.toString(path)
 				+ " Sum of No contigiuos " + sum);*/
 
 		//int arr[] = new int[] {4, 5, 4, 3 };
 		//int arr[] =new int[]{11, 12, -2, -1};
 		//int arr[] =new int[]{-1, 7 ,8, -5, 4};
 		int arr[]=new int[]{3,2, 1 ,-1};
 		//OnlyPathWithMaxSumSubseqwithNonContigious(arr);
 		ppp(arr);
 		
 	}
}