package com.kartik.chem;

//SquirmCellSlot.java

/**
* Cell Slot.
*/
public class SquirmCellSlot {

 protected boolean has_occupant;
 protected SquirmCell occupant;

 // Public constructor initializes size of grid
 public SquirmCellSlot() {
     has_occupant = false;
 }

 public void makeOccupied(SquirmCell occ) {
     // !has_occupant is a necessary condition for calling this function
     if (has_occupant)
         throw new Error("SquirmCellSlot::makeOccupied : already occupied!");

     has_occupant = true;
     occupant = occ;
 }

 public void makeEmpty() {
     has_occupant = false;
 }

 public boolean queryEmpty() {
     return !has_occupant;
 }

 public SquirmCell getOccupant() {
     // has_occupant is a necessary condition for calling this function
     if (!has_occupant)
         throw new Error("SquirmCellSlot::getOccupant : no occupant!");

     return occupant;
 }
} // End of the Class //