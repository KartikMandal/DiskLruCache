package com.kartik.org;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Demo {

	public static void main(String[] args) {
		Set<String> set = new HashSet<>(Arrays.asList("C++", "Java","JSP","Algo"));

		String[] array = set.toArray(new String[0]);
		System.out.println(Arrays.toString(array));
		StringBuilder sb=new StringBuilder();
		int i=0;
		for (String s: set){
			if(i<set.size()-1){
			sb.append(s).append(',');
			}else{
				sb.append(s);
			}
		i++;
	}
		System.out.println(sb.toString());
	}

}
