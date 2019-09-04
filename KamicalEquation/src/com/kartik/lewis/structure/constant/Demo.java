package com.kartik.lewis.structure.constant;

public class Demo {

	public static void main(String[] args) {
		//String data="CH3CHO";
		//String data="CH3COCH2CH3";
		String data="CH3COOCH2CH3";
		if(data.matches("(.*)CHO(.*)")){
			System.out.println("CHO");
		}else if(data.matches("(.*)CONH(.*)")){
			System.out.println("CONH");
		}else if(data.matches("(.*)COCH(.*)")){
			System.out.println("COCH");
		}else if(data.matches("(.*)COOH(.*)")){
			System.out.println("COOH");
		}else if(data.matches("(.*)COO(.*)")){
			System.out.println("COO");
		}else if(data.matches("(.*)NH(.*)")){
			System.out.println("NH");
		}

	}

}
