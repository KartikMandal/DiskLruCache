package com.mandal.org;
//https://wilddiary.com/printing-numbers-in-sequence-from-alternating-threads/
public class SequencePrinter {
	 public static void main(String[] args) {
	        // Create the workers
	        Worker w1 = new Worker();
	        Worker w2 = new Worker();
	        Worker w3 = new Worker();
	        
	        // chain them in a round robin fashion
	        w1.setNext(w2);
	        w2.setNext(w3);
	        w3.setNext(w1);
	        
	        // Create named threads for the workers
	        Thread t1 = new Thread(w1, "Thread-1 - ");
	        Thread t2 = new Thread(w2, "Thread-2 - ");
	        Thread t3 = new Thread(w3, "Thread-3 - ");
	        
	        // start the threads
	        t1.start();
	        t2.start();
	        t3.start();
	        
	        // Seed the first worker
	        w1.accept(1);
	    }
}
