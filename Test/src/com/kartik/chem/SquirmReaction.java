package com.kartik.chem;

import org.apache.log4j.Logger;

//SquirmReaction.java

class SquirmReaction {

 private static final Logger LOGGER = Logger.getLogger(SquirmReaction.class);
 
 public char us_type;
 public int us_state;
 public boolean current_bond;
 public char them_type;
 public int them_state;
 public int future_us_state;
 public boolean future_bond;
 public int future_them_state;

 public SquirmReaction(char us_type, int us_state, boolean current_bond, char them_type, int them_state,
         int future_us_state, boolean future_bond, int future_them_state) {
     this.us_type = us_type;
     this.us_state = us_state;
     this.current_bond = current_bond;
     this.them_type = them_type;
     this.them_state = them_state;
     this.future_us_state = future_us_state;
     this.future_bond = future_bond;
     this.future_them_state = future_them_state;        
 }
};