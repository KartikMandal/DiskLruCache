package com.kartik.chem.test;

import java.util.ArrayList;
import java.util.List;

public class ListPermutation<E> {

	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add("K");
		list.add("Mn");
		list.add("O");
		list.add("H");
		list.add("Cl");
		ListPermutation<String> jj=new ListPermutation<String>();
		System.out.println(jj.generatePerm(list));
	}
	public List<List<E>> generatePerm(List<E> original) {
	     if (original.size() == 0) { 
	       List<List<E>> result = new ArrayList<List<E>>();
	       result.add(new ArrayList<E>());
	       return result;
	     }
	     E firstElement = original.remove(0);
	     List<List<E>> returnValue = new ArrayList<List<E>>();
	     List<List<E>> permutations = generatePerm(original);
	     for (List<E> smallerPermutated : permutations) {
	       for (int index=0; index <= smallerPermutated.size(); index++) {
	         List<E> temp = new ArrayList<E>(smallerPermutated);
	         temp.add(index, firstElement);
	         returnValue.add(temp);
	       }
	     }
	     return returnValue;
	   }
}
