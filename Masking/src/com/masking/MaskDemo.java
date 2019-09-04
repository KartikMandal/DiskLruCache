package com.masking;

public class MaskDemo {

	public static void main(String[] args) {
		Mask mask=new Mask();
		//String data="dgdsg565656 678TT675-098765AA 7657667,6767RR5565";
		String data="7-11 866-216-1072 WA";
		System.out.println(data);
		System.out.println(mask.maskValue(data));

	}

}
