package com.mandal.org;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSequence implements Runnable {

	AtomicInteger sharedOutput = new AtomicInteger(0);
	private Object object = new Object();
	private final int threadPosition;
    public ThreadSequence(int threadPosition) {
            super();
            this.threadPosition = threadPosition;
    }
	@Override
	public void run() {
      while (sharedOutput.get() < 9) {
         synchronized (object) {
             if (sharedOutput.get() % 3 == this.threadPosition) {

                 if(sharedOutput.get() < 9)
                 System.out.println(Thread.currentThread()+"  "+sharedOutput.incrementAndGet());
             }
         }
     }
		
	}

}
