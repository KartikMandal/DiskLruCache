package com.practice.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Kartik Mandal
 * {@link https://java2blogs.blogspot.in/}
 *
 */
public class RepeatedSubsequence {

	public static void main(String[] args) {
		String str = "AABEBCDD";
		RepeatedSubsequence rr = new RepeatedSubsequence();
		System.out.println(rr.repeatedSubsequence(str));
	}

	public String repeatedSubsequence(String data) {
		Map<Character, Character> map = new HashMap<Character, Character>();
		List<Character> list = new ArrayList<Character>();

		char[] X = data.toCharArray();
		for (char c : X) {
			if (!map.containsValue(c)) {
				map.put(c, c);
			} else {
				if(!list.contains(c)){
				list.add(c);
				}
			}
		}
		StringBuffer br = new StringBuffer();
		for (char d : list) {
			br.append(d);
		}

		return br.toString();

	}
}
