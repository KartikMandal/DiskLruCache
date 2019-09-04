package com.send.sms;

public class DemoAbstract extends MochaHost {

	public static void main(String[] args) {
		DemoAbstract d=new DemoAbstract();
		d.add();
		d.add(10,20);
		MochaHost.add(20);

	}

	@Override
	public void add() {
		System.out.println("This is absctruct method impl");
		
	}

}
