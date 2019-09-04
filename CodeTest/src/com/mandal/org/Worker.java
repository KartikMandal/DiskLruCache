package com.mandal.org;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
//https://wilddiary.com/printing-numbers-in-sequence-from-alternating-threads/
public class Worker implements Runnable{
    
BlockingQueue<Integer> q = new LinkedBlockingQueue<Integer>(); 
Worker next = null; // next worker in the chain

public void setNext(Worker t) {
    this.next = t;
}

public void accept(int i) {
    q.add(i);
}

@Override
public void run() {
    while (true){
        try {
            int i = q.take(); // blocks till it receives a number
            
            System.out.println( Thread.currentThread().getName() +  i);
            
            Thread.sleep(1000); // delay to slow the printing
            if (next != null){
                next.accept(i+1); // pass the next number to the next worker 
            }

        } catch (InterruptedException e) {
            System.err.println( Thread.currentThread().getName() + " interrrupted.");
        }
    }
}
}