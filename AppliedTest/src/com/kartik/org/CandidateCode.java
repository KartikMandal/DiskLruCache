package com.kartik.org;

import java.io.*;
import java.util.*;
public class CandidateCode {
	/*public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			// First line contains T which indicates the number of days for
			// which the delivery is to be made.
			int T = scanner.nextInt();
			// 1 <= T <= 400
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
						// 1 <= weight of each cake <= 1000
						if (ticket <-1000 && ticket > 1000) {
							return;
						} else {
							// System.out.printf(ticket+" ");
							nebourgh[k] = ticket;
						}
					}
					int[] sum=OnlyPathWithMaxSumSubseqwithNonContigious(nebourgh);
					String rev ="";
					for (int i=sum.length-1;i>=0;i--) {
						if(sum[i]>0){
						rev+=sum[i];
						}
					}
					System.out.println(rev);
				}
			}
		}catch(Exception e){
			System.out.println();
		}
	

	}*/
	
	/*
	 * Function to return max sum such that no two elements are adjacent
	 */
	static int FindMaxSum(int arr[]) {
		int a[] = new int[arr.length];
		int incl = arr[0];
		int excl = 0;
		int excl_new;
		int i;

		for (i = 1; i < arr.length; i++) {
			/* current max excluding i */
			excl_new = (incl > excl) ? incl : excl;

			/* current max including i */
			incl = excl + arr[i];
			excl = excl_new;
		}

		/* return max of incl and excl */
		return ((incl > excl) ? incl : excl);
	}

	static PathAndSum p;

	public static class PathAndSum {
		int[] path;
		int sum;
	}

	// DP solution to calculate maximum sum in the given array with
	// no adjacent elements considered (Function uses extra space)
	public static void pathWithMaxSumSubseqwithNonContigious(int[] A) {
		int n = A.length;
		List<Integer> s = new ArrayList<>();
		int path[] = new int[A.length];
		// base case
		if (n == 1) {
			path[0] = A[0];
			p = new PathAndSum();
			p.path = path;
			p.sum = A[0];
			return;
		}
		// create an auxiliary array to store solution of sub-problems
		int[] lookup = new int[n];
		// lookup[i] stores the maximum sum possible till index i
		// trivial case
		lookup[0] = A[0];
		lookup[1] = Integer.max(A[0], A[1]);

		// traverse array from index 2
		for (int i = 2; i < n; i++) {
			// lookup[i] stores the maximum sum we get by
			// 1. excluding current element & take maximum sum till index i-1
			// 2. including current element A[i] and take maximum sum
			// till index i-2
			lookup[i] = Integer.max(lookup[i - 1], lookup[i - 2] + A[i]);
			// if current element is more than max sum till current element
			lookup[i] = Integer.max(lookup[i], A[i]);
		}
		for (int i = n - 1; i >= 0; i = i - 2) {
			s.add(lookup[i]);
		}
		Collections.reverse(s);
		path = new int[s.size()];
		int start = 0, count = 0;

		for (int i : s) {
			path[count] = i - start;
			start = i;
			count++;
		}
		p = new PathAndSum();
		p.path = path;
		p.sum = lookup[n - 1];
	}

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
		if(lookup[1]>0)
		list.add(lookup[1]);
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
       //System.out.println(v); 
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
				+ " Sum of No contigiuos " + sum);
*/
		int arr[] = new int[] {4, 5, 4, 3 };
		//int arr[] =new int[]{11, 12, -2, -1};
		//int arr[] =new int[]{-1, 7 ,8, -5, 4};
		OnlyPathWithMaxSumSubseqwithNonContigious(arr);
		
	}
}
