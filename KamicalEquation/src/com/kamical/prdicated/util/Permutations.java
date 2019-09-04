/*
* Copyright (c) 2018
* kamical india pvt Ltd.
* village+PO saharda,Ps Pingla
* Dist Paschim Medinipur
* State WestBengal
* Pin 721131
*
* All rights reserved.
*
* Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
*
* 1. Redistributions of source code must retain the above copyright notice, this
*    list of conditions and the following disclaimer.
* 2. Redistributions in binary form must reproduce the above copyright notice,
*    this list of conditions and the following disclaimer in the documentation
*    and/or other materials provided with the distribution.
* 3. Neither the name of the the copyright holder nor the
*    names of its contributors may be used to endorse or promote products
*    derived from this software without specific prior written permission.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
* ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
* ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*/
package com.kamical.prdicated.util;

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
		// Integer permutation
		Permutations<Integer> obj = new Permutations<Integer>();
		Collection<Integer> input = new ArrayList<Integer>();
		input.add(1);
		input.add(2);
		input.add(3);
		Collection<List<Integer>> output = obj.permute(input);
		// int k = 0;
		Set<List<Integer>> pnr = null;
		for (int i = 0; i <= input.size(); i++) {
			pnr = new HashSet<List<Integer>>();
			for (List<Integer> integers : output) {
				pnr.add(integers.subList(i, integers.size()));
			}
			// k = input.size()- i;
			// System.out.println("P("+input.size()+","+k+") :"+
			// "Count ("+pnr.size()+") :- "+pnr);
		}
		System.out.println("Character permutation and combination below:");
		// String permutation
		Permutations<String> objStr = new Permutations<String>();
		Collection<String> inputStr = new ArrayList<String>();
		inputStr.add("K");
		inputStr.add("Mn");
		inputStr.add("O");
		inputStr.add("H");
		inputStr.add("Cl");
		/* inputStr.add("I"); */
		Collection<List<String>> outputStr = objStr.permute(inputStr);
		int len = 0;
		Set<List<String>> pnrStr = null;
		for (int i = 0; i <= inputStr.size(); i++) {
			pnrStr = new HashSet<List<String>>();
			for (List<String> integers : outputStr) {
				pnrStr.add(integers.subList(i, integers.size()));
			}
			len = input.size() - i;
			System.out.println("P(" + inputStr.size() + "," + len + ") :"
					+ "Count (" + pnrStr.size() + ") :- " + pnrStr);
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
