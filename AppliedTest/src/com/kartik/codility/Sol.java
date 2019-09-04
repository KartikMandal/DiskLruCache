package com.kartik.codility;

import java.util.Arrays;

public class Sol {

	
	 /* prints element and NGE pair for  
    all elements of arr[] of size n */
   static void printNGE(int arr[], int n) 
   { 
       int next, i, j; 
       for (i=0; i<n; i++) 
       { 
           next = -1; 
           for (j = i+1; j<n; j++) 
           { 
               if (arr[i] < arr[j]) 
               { 
                   next = arr[j]; 
                   break; 
               } 
           } 
           System.out.println(arr[i]+" -- "+next); 
       } 
   } 
   
  public static String sortingComparision(int[] a,int []b){
	  for (int i = 0; i < b.length; i++) {
		  if(a[i]>b[i]){
			  continue;
		  }else{
			  return "LOSE";
		  }
		  
	}
	return "WIN"; 
	 
   }
   
   
	public static void main(String[] args) {
		//int[]a=new int[]{500, 789, 234, 400, 452, 150};
		//int[]b=new int[]{112, 243, 512, 343, 90, 478};
		//10 20 50 100 500 400 
		//30 20 60 70 90 490
		int[]a=new int[]{30, 20, 60, 70, 90, 490};
		int[]b=new int[]{10, 20, 50, 100, 500, 400};
		Arrays.sort(a);
		Arrays.sort(b);
		System.out.println(sortingComparision(a,b));
	}

}
