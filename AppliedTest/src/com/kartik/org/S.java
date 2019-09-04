package com.kartik.org;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class S {

	/*public static void main(String[] args) {
		//int arr[] = new int[]{5, 5, 10, 100, 10, 5};
		//int arr[] =new int[]{-1, 7, 8, -5, 4};
		//int arr[] =new int[]{3, 2, 1, -1};
		//int arr[] =new int[]{4, 5, 4, 3};
		int arr[] =new int[]{5, 10, 4, -1};
		//int arr[] =new int[]{11, 12, -2, -1};


		List<Integer> list1=new ArrayList<Integer>();
		List<Integer> list2=new ArrayList<Integer>();
        System.out.println(FindMaxSum(arr, arr.length,list1,list2));

	}*/

	
	/*Function to return max sum such that no two elements 
    are adjacent */
 static int FindMaxSum(int arr[], int n,List<Integer> list1,List<Integer> list2) 
  { 
      int incl = arr[0];
      int excl = 0; 
     // if(incl>excl)
     // list1.add(arr[0]);
      int excl_new; 
      int i; 

      for (i = 1; i < n; i++) 
      { 
          /* current max excluding i */
          excl_new = (incl > excl) ? incl : excl; 
          
          if(incl > excl){
				list1.add(arr[i]);
			}else{
				list2.add(arr[i]);
			}
          /* current max including i */
          incl = excl + arr[i]; 
          
          excl = excl_new; 
      } 

      /* return max of incl and excl */
      return ((incl > excl) ? incl : excl); 
  } 
 
 

 	// Function to calculate maximum sum in the given array
 	// with no adjacent elements considered
 	// i --> index of current element
 	// prev --> index of previous element included in sum
 	public static int maxSumSubseq(int[] arr, int i, int n, int prev,List<Integer> list1)
 	{
 		// base case: all elements are processed
 		if (i == n) {
 			return 0;
 		}

 		// recurse by excluding current element
 		int excl = maxSumSubseq(arr, i + 1, n, prev,list1);

 		int incl = 0;

 		// include current element only if it is not adjacent
 		// to previous element considered
 		if (prev + 1 != i) {
 			incl = maxSumSubseq(arr, i + 1, n, i,list1) + arr[i];
 			list1.add(arr[i]);
 		}

 		// return maximum sum we get by including or excluding
 		// current item
 		return Integer.max(incl, excl);
 	}

 	// main function
 	/*public static void main(String[] args)
 	{
 		List<Integer> list1=new ArrayList<Integer>();
 		int[] A = { 1, 2, 9, 4, 5, 0, 4, 11, 6 };
 		System.out.print("Maximum sum is " 
 				+ maxSumSubseq(A, 0, A.length, Integer.MIN_VALUE,list1));
 	}*/
 	
 	
 	
 // DP solution to calculate maximum sum in the given array with
 	// no adjacent elements considered (Function uses extra space)
 	public static int maxSumSubseq(int[] A,List<Integer> list1,List<Integer> list2)
 	{
 		int n = A.length;

 		// base case
 		if (n == 1) {
 			return A[0];
 		}

 		// create an auxiliary array to store solution of sub-problems
 		int[] lookup = new int[n];

 		// lookup[i] stores the maximum sum possible till index i

 		// trivial case
 		lookup[0] = A[0];
 		list1.add(A[0]);
 		lookup[1] = Integer.max(A[0], A[1]);
 		list2.add(lookup[1]);
 		// traverse array from index 2
 		for (int i = 2; i < n; i++)
 		{
 			// lookup[i] stores the maximum sum we get by

 			// 1. excluding current element & take maximum sum till index i-1
 			// 2. including current element A[i] and take maximum sum
 			//	till index i-2
 			if(lookup[i - 1]< lookup[i - 2] + A[i]){
 				list1.add(A[i]);
 			}else{
 				list2.add(A[i]);
 			}
 			lookup[i] = Integer.max(lookup[i - 1], lookup[i - 2] + A[i]);
 			
 			// if current element is more than max sum till current element
 			lookup[i] = Integer.max(lookup[i], A[i]);
 		}

 		// return maximum sum
 		return lookup[n - 1];
 	}
 	
 	
 	public static int maxSumSubSeqNonContagious(int a[]){
 		int max_include[] = new int[a.length];
 		int max_exclude[] = new int[a.length];
 		List<Integer> list1=new ArrayList<Integer>();
 		List<Integer> list2=new ArrayList<Integer>();
 		max_include[0] = a[0];
 		max_exclude[0] = Integer.MIN_VALUE;
 		int max = a[0];
 		for(int i = 1; i<a.length; i++){
 			/*if(max_exclude[i-1]+a[i]>a[i]){
 				list1.add(a[i]);
 				swap(list1,list2);
 			}else{
 				list2.add(a[i]);
 				swap(list1,list2);
 			}*/
 			
 			max_include[i] = Math.max(max_exclude[i-1]+a[i], a[i]);
 			//swap(list1,list2);
 			max_exclude[i] = Math.max(max_include[i-1], max_exclude[i-1]);
 			//swap(list1,list2);
 			max = Math.max(max_include[i], max_exclude[i]);
 		}
 		
 		return max;
 	}
 	
 	public static void swap(List<Integer> list1,List<Integer> list2){
 		List<Integer> temp;
 		temp=list1;
 		list1=null;
 		list1=list2;
 		list2=null;
 		list2=temp;
 		
 	}
 	
 	public static  int maxSum(int[] arr){
        int x = arr[0];
        int y = arr[0]>arr[1]? arr[0]:arr[1];
        int f[]=new int[arr.length];
        int s[]=new int[arr.length];
        int z = 0;
        int cnt1=0;
        int cnt2=0;
        if(arr.length<=2)
         {
             if(arr.length ==1 ){
            	 f[cnt1]=x;
                return x;
             }
             if(arr.length == 2){
            	 s[cnt2]=y;
                return y;
             }
         }
        int temp = x>y? x:y;
        for(int i=2;i<arr.length;i++){
        	if(x+arr[i]>y){
        		f[cnt1]=arr[i];
        		cnt1++;
        	}
            z  = x+arr[i]>y? x+arr[i]:y;
            x = y;
            y = z;
        }
           return z;
    }
 	// main function
 	/*public static void main(String[] args)
 	{
 		List<Integer> list1=new ArrayList<Integer>();
 		List<Integer> list2=new ArrayList<Integer>();
 		//int[] A = { 1, 2, 9, 4, 5, 0, 4, 11, 6 };
 		int A[] = new int[]{5, 5, 10, 100, 10, 5};
 				//int arr[] =new int[]{-1, 7, 8, -5, 4};
 				//int arr[] =new int[]{3, 2, 1, -1};
 				//int arr[] =new int[]{4, 5, 4, 3};
 				//int A[] =new int[]{5, 10, 4, -1};
 				//int arr[] =new int[]{11, 12, -2, -1};
 		//System.out.print("Maximum sum is " + maxSumSubseq(A,list1,list2));
 		System.out.print("Maximum sum is " + maxSumSubSeqNonContagious(A));
 	}*/

 	public static int sumNonAdjacent(int[] arr)
	{
		int[] sum = new int[arr.length]; // store the max sum upto particular element
		Map<Integer,Integer> path1=new LinkedHashMap<>();
		sum[0] = arr[0];
		sum[1] = Math.max(arr[0],arr[1]);
		if(arr[0]>arr[1]){
		path1.put(sum[0],sum[0]);
		}else{
			path1.put(sum[0],sum[0]);
			path1.put(sum[1],sum[1]);
		}
		for(int i=2; i<arr.length; i++)
		{
			int temp = Math.max(arr[i], arr[i] + sum[i-2]);
			sum[i] = Math.max(temp, sum[i-1]);
			if(temp>sum[i-1]){
				if(arr[i]>arr[i] + sum[i-2]){
				path1.put(arr[i],arr[i]);
				}else{
					path1.put(arr[i] + sum[i-2],arr[i]);
				}
			  }
		}
		
		Map<Integer, Integer> reverseSortedMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		 
		reverseSortedMap.putAll(path1);
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
		 for (Integer integer : list) {
			System.out.print(integer+" ");
		}
		 
		return sum[arr.length-1];

		
		/**
		  * variation to this problem : if array is circular then
          * Find maximum sum from left to right ignoring first element.
          * Find maximum sum from right to left ignoring last element.
          * Maximum of two will be the answer. It gurantees that both first and last element
          * will be not selected together.
       */
		
	}
	public static void main(String[] args) 
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
		
	}

}
