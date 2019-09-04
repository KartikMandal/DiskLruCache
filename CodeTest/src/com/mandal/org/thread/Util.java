package com.mandal.org.thread;

public class Util {

	 // Util class to sleep a thread 
    static void sleep(long millis) 
    { 
        try
        { 
            Thread.sleep(millis); 
        } 
        catch (InterruptedException e) 
        { 
            e.printStackTrace(); 
        } 
    } 
}
