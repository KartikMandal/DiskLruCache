package com.mandal.org;

public class ThreeThreadsSequence {

	public static void main(String[] args) {
			ThreadSequence t1=new ThreadSequence(0);
			ThreadSequence t2=new ThreadSequence(1);
			ThreadSequence t3=new ThreadSequence(2);
		 	Thread ts1 = new Thread(t1);
	        Thread ts2 = new Thread(t2);
	        Thread ts3 = new Thread(t3);
	        ts1.setName("Thread1");
	        ts2.setName("Thread2");
	        ts3.setName("Thread3");
	        ts1.start();
	        ts2.start();
	        ts3.start();

	}

}
