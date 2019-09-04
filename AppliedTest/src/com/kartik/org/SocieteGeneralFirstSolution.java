package com.kartik.org;

import java.util.Arrays;
import java.util.Scanner;

public class SocieteGeneralFirstSolution {

	public static void main(String[] args) {
		solutionMethod();
		
		/*
		
		int []arr ={20,10,8,7};
		calculateCalory(arr);
		int []arr1 ={2, 76, 98, 88, 97, 33, 56, 31};
		calculateCalory(arr1);
		
		int []arr12 ={71, 18, 78, 55, 48, 54, 46, 16};
		calculateCalory(arr12);
		
		int []aa={1, 20 ,4 ,200, 1, 100, 1093, 10, 1001, 11};
		calculateCalory(aa);*/
	}

	
	private static void solutionMethod() {
		Scanner scanner = new Scanner(System.in);
		try{
		int totalTestCaeNumber = scanner.nextInt();
		if(totalTestCaeNumber<=0 && totalTestCaeNumber>Integer.MAX_VALUE){
			return;
		}
		for (int j = 0; j < totalTestCaeNumber; j++) {
			int numberOfDays = scanner.nextInt();
			if(numberOfDays<=0 && numberOfDays>Integer.MAX_VALUE){
				return;
			}else{
			Integer[] kilometers = new Integer[numberOfDays];
			for (int i = 0; i < numberOfDays; i++) {
				int km=scanner.nextInt();
					if (km <= 0 && km>Integer.MAX_VALUE) {
						return;
					} else {
						kilometers[i] = km;
					}
			}
			
			calculateCalory(kilometers);
			//list.add(kilometers);
			}
		}
		
		/*for (Integer[] integers : list) {
			calculateCalory(integers);
		}*/
		}catch(NumberFormatException n){
		throw new NumberFormatException("Number is not there");	
		}catch(Exception e){
			throw new NumberFormatException("Exception occure");
		}
		
	}
	/**
	 * 
	 * @param integers
	 */
	private static void calculateCalory(Integer[] integers) {
		Arrays.sort(integers);
		long cal = 0;
		long totalCalBurn = 0;
		for (int j = integers.length - 1; j >= 0; j--) {
			totalCalBurn += (2 * cal + integers[j]);
			cal += integers[j];
		}

		System.out.println(totalCalBurn);
	}
	
}
