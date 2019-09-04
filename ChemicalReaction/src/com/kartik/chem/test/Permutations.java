package com.kartik.chem.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 
 * @author kartik 
 * Blog www.kartikchandramandal.blogspot.com
 * One of the best way to do permutation of a data set is to by applying permutation , it help making all combination of a specified length 
 */
public class Permutations<T> {
public static void main(String args[]) {
System.out.println("Integer permutation below:");
//Integer permutation
Permutations<Integer> obj = new Permutations<Integer>();
Collection<Integer> input = new ArrayList<Integer>();
input.add(1);
input.add(2);
input.add(3);
Collection<List<Integer>> output = obj.permute(input);
//int k = 0;
Set<List<Integer>> pnr = null;
for (int i = 0; i <= input.size(); i++) {
pnr = new HashSet<List<Integer>>();
for(List<Integer> integers : output){
pnr.add(integers.subList(i, integers.size()));
}
//k = input.size()- i;
//System.out.println("P("+input.size()+","+k+") :"+ "Count ("+pnr.size()+") :- "+pnr);
}
System.out.println("Character permutation and combination below:");
 //String permutation
Permutations<String> objStr = new Permutations<String>();
Collection<String> inputStr = new ArrayList<String>();
inputStr.add("K");
inputStr.add("Mn");
inputStr.add("O");
inputStr.add("H");
inputStr.add("Cl");
/* inputStr.add("I");*/
Collection<List<String>> outputStr = objStr.permute(inputStr);
int len = 0;
Set<List<String>> pnrStr = null;
for (int i = 0; i <= inputStr.size(); i++) {
pnrStr = new HashSet<List<String>>();
for(List<String> integers : outputStr){
pnrStr.add(integers.subList(i, integers.size()));
}
 len = input.size()- i;
 System.out.println("P("+inputStr.size()+","+len+") :"+ "Count ("+pnrStr.size()+") :- "+pnrStr);
}
}
 public Collection<List<T>> permute(Collection<T> input) {
 Collection<List<T>> output = new ArrayList<List<T>>();
 if (input.isEmpty()) {
 output.add(new ArrayList<T>());
 return output;
}
 List<T> list = new ArrayList<T>(input);
 T head = list.get(0);
 List<T> rest = list.subList(1, list.size());
 for (List<T> permutations : permute(rest)) {
 List<List<T>> subLists = new ArrayList<List<T>>();
 for (int i = 0; i <= permutations.size(); i++) {
 List<T> subList = new ArrayList<T>();
 subList.addAll(permutations);
 subList.add(i, head);
 subLists.add(subList);
}
output.addAll(subLists);
}
 return output;
}
}
