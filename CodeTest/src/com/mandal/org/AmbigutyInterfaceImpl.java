package com.mandal.org;

public class AmbigutyInterfaceImpl implements AmbigutyInterface {

	@Override
	public void sort(String s) {
		System.out.println("String");
		
	}

	@Override
	public void sort(Integer I) {
		System.out.println("Integer");
		
	}

	@Override
	public void sort(Object o) {
		System.out.println("Object");
		
	}

}
