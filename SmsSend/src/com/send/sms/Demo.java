package com.send.sms;

public class Demo {

	public static void main(String[] args) {
		KartikInterfaceImpl obj1 = new KartikInterfaceImpl();
		if (obj1 instanceof KartikInterface) {
            System.out.println("A grade college.");
		}
		obj1.add(10,20);
	}

}
