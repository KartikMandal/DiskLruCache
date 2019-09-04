package com.send.sms;

public abstract class MochaHost {
	
	public abstract void add();
	
	 void add(int i, int j){System.out.println("Add with void");};
	 
	static final void add(int i){System.out.println("Add with static and void");}
}
