package com.kartik.chem;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

/**
 * Main entry point.
 * @author bbrown
 *
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);
    
    /**
     * Main entry point.
     */
    public static void main(final String [] args) {        
        LOGGER.info(">>> Running");
        final Squirm frame = new Squirm();
        frame.setTitle("Squirm Artificial Chemistry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(20, 20);    
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setFocusable(true);        
        frame.setVisible(true);  
        frame.init();
        frame.start();        
        LOGGER.info(">>> Done");       
    }
    
} // End
