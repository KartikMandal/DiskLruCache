package com.mandal.org;

public class AmbigutyMethod {

	public static void main(String[] args) {
		
		AmbigutyInterface a=new AmbigutyInterfaceImpl();
		//this is correct because it is calling string object method
		a.sort("null");
		//Below line is not correct because 
		//the method sort(String) is ambiguous for the type of AmbigutyInterface
		//So which is the first method of interface that is call 
		//like the method sort(Interger) is ambiguous for the type of AmbigutyInterface 
		//a.sort(null);
	}

}
