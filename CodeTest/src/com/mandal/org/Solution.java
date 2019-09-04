package com.mandal.org;

public class Solution {
	/**
	 * 
	 * @param A
	 * @return
	 */
	public static int solution(int A) {
		if(A<0 || A>Integer.MAX_VALUE){ 
			return -1;
		}else{
		String number=String.valueOf(A);
		if (number == null) return -1;
		StringBuffer shuffle = new StringBuffer();
		int mid =(number.length() - 1)/2;
		for (int i = 0,j=number.length() - 1; i <=mid && j>=mid; i++,j--) {
			if(i==j){
				shuffle.append(number.charAt(i));
			}else{
			shuffle.append(number.charAt(i)+""+number.charAt(j));
			}
		}
		
		return Integer.parseInt(shuffle.toString());
		}
	}
	public static void main(String[] args) {
		int a=2341;
		
		System.out.println(solution(a) );
	}

}
