package com.test.demo;

public class MultiPleDelimeterSpliting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String abc=" KARTIK AND MANDAL OR SAHARDA";
		String[]tokens = abc.split("AND|OR");
		
		System.out.println("tokens"+tokens);
	}

}
