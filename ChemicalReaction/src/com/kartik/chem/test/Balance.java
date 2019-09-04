package com.kartik.chem.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Balance {
	/* Primary method that balances an input chemical equation. */
	private String equationBalance(String equation) {
	    // initialize reactant and product ArrayLists
		ArrayList<String> rterms = new ArrayList<String>();
		ArrayList<String> pterms = new ArrayList<String>();
		ArrayList<String> total = new ArrayList<String>();
		
		// used for redox equations
		ArrayList<Double> coeffsr = new ArrayList<Double>();
		ArrayList<Double> coeffsp = new ArrayList<Double>();
		
		// enclose all code in a large try/catch to catch all possible errors in equation
		try {
		    
		    // split equation into reactants and products, and convert to arrays of compounds
			String reactants = equation.substring(0, equation.indexOf("="));
			String products = equation.substring(equation.indexOf("=") + 1, equation.length());
			reactants = reactants.trim();
			products = products.trim();
			converttoArrayList(reactants, rterms);
			converttoArrayList(products, pterms);
			String rterms1[] = new String[rterms.size()];
			String pterms1[] = new String[pterms.size()];
			for (int i = 0; i < rterms.size(); i++){
				if (Character.isDigit(rterms.get(i).charAt(0))){
					throw new Exception();
				}
				rterms.set(i, rterms.get(i).trim());
				rterms1[i] = rterms.get(i);
			}
			for (int i = 0; i < pterms.size(); i++){
				if (Character.isDigit(pterms.get(i).charAt(0))){
					throw new Exception();
				}
				pterms.set(i, pterms.get(i).trim());
				pterms1[i] = pterms.get(i);
			}
			
			// deal with redox equations
			boolean isredox = configureRedox(rterms, coeffsr);
			isredox = configureRedox(pterms, coeffsp);
			
			// add "1" subscripts and deal with polyatomic ions
			addNums(rterms);
			configureParenthesis(rterms);
			addNums(pterms);
			configureParenthesis(pterms);
			addNums(rterms);
			addNums(pterms);
			
			// equation is now all configured, do pattern matching to extract elements
			ArrayList <String> elements = new ArrayList<String>();
			patternMatch(rterms, elements,rterms);
			patternMatch(pterms, elements,pterms);

            // initialize matrix m in order to solve system of equations
            int size = rterms.size() + pterms.size();
            String h[] = new String[1000];
			double m[][] = null;
			int rows = elements.size();
			if (size > rows){
				m = new double[size][size];
			}
			else if (rows > size){
				m = new double[rows][size];
			}
			else if (rows == size){
				m = new double[size][rows];
			}
			if (isredox){
				m = new double[m.length + 1][m[0].length];
				int c = 0;
				for (int i = 0; i < coeffsr.size(); i++){
					m[m.length - 1][c] = coeffsr.get(i);
					c++;
				}
				for (int i = 0; i < coeffsp.size(); i++){
					if (i == coeffsp.size() - 1){
						m[m.length - 1][c] = coeffsp.get(i);
					}
					else
					{
						m[m.length - 1][c] = coeffsp.get(i) * -1;
						c++;
					}
				}
			}
		/*	Alter matrix form
			w	x	 y	 z
		Na	1	0	-2	 0	|0
		O	1	4	-4	-1	|0
		H	1	2	 0	-2	|0
		S	0	1	-1	 0  |0
		*/
			// add system of equations to matrix m
			total.addAll(rterms);
			total.addAll(pterms);
			char c='a';
			List<MoleElement> molePojoList =new ArrayList<MoleElement>();
			MoleElement moleProject =null;
			for (String string : rterms) {
			addToMatrix(string, m, h,"Before",molePojoList, moleProject,c);
			 if(c <= 'z'){
				 ++c;
			 }
			}
			for (String string : pterms) {
			addToMatrix(string, m, h,"After",molePojoList, moleProject,c);
			if(c <= 'z'){
				 ++c;
			 }
			}
			
			Matrix m2=convertMatrix(molePojoList);
			
			
			double[][] A=m2.content;
			m=A;
			
			// perform RREF to solve system of linear equations
			toRREF(m);
			
			// re-extract balanced equation from solved matrix m
			ArrayList<Double> coefficients = new ArrayList<Double>();
			for (int i = 0; i < m[0].length; i++){
				if (m[i][m[0].length - 1] == 0.0){
					m[i][m[0].length - 1] = 1.0;
				}
				coefficients.add(m[i][m[0].length - 1]);
			}
			Double elem[] = new Double[coefficients.size()];
			long factor = 0;
			int denoms[] = new int[elem.length];
			for (int i = 0; i < elem.length; i++){
				elem[i] = m[i][elem.length - 1];
				denoms[i] = toFraction(elem[i]);
			}
			factor = lcm(denoms);
			int fin[] = new int[elem.length];
			for (int i = 0; i < elem.length; i++){
				elem[i] *= factor;
				fin[i] = (int)Math.abs(Math.floor(elem[i].doubleValue()));
				//fin[i] = (int)Math.ceil(elem[i].doubleValue());
			}
			
			// generate final equation
			String newequ1 = generateFinalEquation(rterms1, pterms1, fin);
			String newequ2 = removeSpaces(newequ1);
			/*if (newequ2.equals(equation1)){
				return null;
			}*/
			return newequ2;
		} catch(Exception e) {
		    return null;
		}
	}
	
	/* Generate final balanced equation from solved linear system of equations. */
	private String generateFinalEquation(String[] rterms1, String[] pterms1, int[] fin) {
	    int cou = 0;
	   // String newequ = "";
	    String newequ1 = "";
		for (int i = 0; i < rterms1.length; i++){
			if (fin[cou] == 1){
				if (i == rterms1.length - 1){
				//	newequ += rterms1[i] + " \u2192 ";
					newequ1 += rterms1[i] + " = ";
				}
				else {
				//	newequ += rterms1[i] + " + ";
					newequ1 += rterms1[i] + " + ";
				}
				cou++;
			}
			else
			{
				if (i == rterms1.length - 1){
				//	newequ += fin[cou] + rterms1[i] + " \u2192 ";
					newequ1 += fin[cou] + rterms1[i] + " = ";
				}
				else {
				//	newequ += fin[cou] + rterms1[i] + " + ";
					newequ1 += fin[cou] + rterms1[i] + " + ";
				}
				cou++;
			}
		}
		for (int i = 0; i < pterms1.length; i++){
			if (fin[cou] == 1){
				if (i == pterms1.length - 1) {
				//	newequ += pterms1[i];
					newequ1 += pterms1[i];
				}
				else {
					//newequ += pterms1[i] + " + ";
					newequ1 += pterms1[i] + " + ";
				}
				cou++;
			}
			else
			{
				if (i == pterms1.length - 1) {
					//newequ += fin[cou] + pterms1[i];
					newequ1 += fin[cou] + pterms1[i];
				}
				else {
					//newequ += fin[cou] + pterms1[i] + " + ";
					newequ1 += fin[cou] + pterms1[i] + " + ";
				}
				cou++;
			}
		}
		return newequ1;
	}
	
	
	/* Perform pattern matching to extract elements into output ArrayList. */
	private void patternMatch(ArrayList<String> terms, ArrayList<String> elements,ArrayList<String> rterms) {
	    for (int i = 0; i < terms.size(); i++){
	        // match single uppercase letter, or uppercase with 1 or 2 lowercase
			Pattern p1 = Pattern.compile("([A-Z])(\\d+)");
			String comp = rterms.get(i);
			Matcher m1 = p1.matcher(comp);
			while (m1.find()) {
				if (!elements.contains(m1.group(1)))
				{
					elements.add(m1.group(1));
				}
			}
			Pattern p2 = Pattern.compile("([A-Z])([a-z])(\\d+)");
			Matcher m2 = p2.matcher(comp);
			while (m2.find()) {
				if (!elements.contains(m2.group(1) + "" + m2.group(2)))
				{
					elements.add(m2.group(1) + "" + m2.group(2));
				}
			}
			Pattern p3 = Pattern.compile("([A-Z])([a-z])([a-z])(\\d+)");
			Matcher m3 = p3.matcher(comp);
			while (m3.find()) {
				if (!elements.contains(m3.group(1) + "" + m3.group(2) + "" + m3.group(3)))
				{
					elements.add(m3.group(1) + "" + m3.group(2) + "" + m3.group(3));
				}
			}
			if (rterms.get(i).length() == 1){
				String e = rterms.get(i);
				rterms.set(i, e + "1");
			}
		}

	}
	
	/* Add the terms (either products or reactants) to a matrix m in order to solve the RREF of the
	   system of linear equations involved in the chemical equation.
	*/
	private void addToMatrix(String comp, double[][] m, String[] h,String equalSign,List<MoleElement> molePojoList,
			MoleElement moleProject,char variable) {
	    String sign=(equalSign.equalsIgnoreCase("Before")?"+":"-");
			
			String tempString=null;
			/*
			 Pattern p1 = Pattern.compile("([A-Z])(\\d+)");
			 Matcher m1 = p1.matcher(comp);
			Pattern p2 = Pattern.compile("([A-Z])([a-z])(\\d+)");
			Matcher m2 = p2.matcher(comp);
			Pattern p3 = Pattern.compile("([A-Z])([a-z])([a-z])(\\d+)");
			Matcher m3 = p3.matcher(comp);*/
				PeriodicTable constantElement=new PeriodicTable();
		     	constantElement.setInitialValues();
		     	Hashtable<String,Element> hm=constantElement.getHm();
				moleProject=new MoleElement();
				moleFactor(molePojoList, moleProject, comp.length(), comp, tempString, variable, sign, hm);
	}
	
	/* Determine if an equation is a redox equation and add
	   oxidation/reduction coefficients to coeffs array.
	*/
	private boolean configureRedox(ArrayList<String> terms, ArrayList<Double> coeffs) {
	    boolean isredox = false;
		for (int i = 0; i < terms.size(); i++){
			String x = terms.get(i);
			for (int j = 0; j < x.length(); j++){
				if (j == x.length() - 1 && x.charAt(j) != ']'){
					coeffs.add(0.0);
				}
				if (x.charAt(j) == '['){
					isredox = true;
					int ind = x.indexOf(']');
					String num = x.substring(j + 1, ind);
		    		coeffs.add(Double.parseDouble(num));
					x = x.substring(0, j);
					break;
				}
			}
			terms.set(i, x);
		}
		return isredox;
	}
	
	/* Removes all spaces from input string s. */
	private String removeSpaces(String s) {
		StringTokenizer st = new StringTokenizer(s, " ", false);
		String t = "";
		while (st.hasMoreElements()) t += st.nextElement();
		return t;
	}
	
	

    /**
     * 
     * @param numbers
     * @return
     */
	public static long lcm(int[] numbers) {
        long lcm = 1;
        int divisor = 2;
        while (true) {
            int cnt = 0;
            boolean divisible = false;
            for (int i = 0; i < numbers.length; i++) {
                /**
                 * lcm (n1,n2,... 0)=0.For negative number we convert into
                 * positive and calculate lcm.
                 */
                if (numbers[i] == 0) {
                    return 0;
                } else if (numbers[i] < 0) {
                    numbers[i] = numbers[i] * (-1);
                }
                if (numbers[i] == 1) {
                    cnt++;
                }
                /**
                 * divide numbers by devisor if complete division i.e. without
                 * remainder then replace number with quotient; used for find
                 * next factor
                 */
                if (numbers[i] % divisor == 0) {
                    divisible = true;
                    numbers[i] = numbers[i] / divisor;
                }
            }
            /**
             * If divisor able to completely divide any number from array
             * multiply with lcm and store into lcm and continue to same divisor
             * for next factor finding. else increment divisor
             */
            if (divisible) {
                lcm = lcm * divisor;
            } else {
                divisor++;
            }
            /**
             * Check if all numbers is 1 indicate we found all factors and
             * terminate while loop.
             */
            if (cnt == numbers.length) {
                return lcm;
            }
        }
    }
	public static int lcm2(int num1, int num2) {
        if(num1==0 || num2==0){
            return 0;
        }else if(num1<0){
            num1=num1*(-1);
        }else if(num2<0){
            num2=num2*(-1);
        }
        int m = num1;
        int n = num2;
        while (num1 != num2) {
            if (num1 < num2) {
                num1 = num1 + m;
            } else {
                num2 = num2 + n;
            }
        }
        return num1;
    }
	/* Returns the denominator of the fraction represented by the input decimal value. */
	public static int toFraction(double decimal) {
		int LIMIT = 12;
		int denominators[] = new int[LIMIT + 1];
		int numerator, denominator = 0, temp;
		int MAX_GOODNESS = 100;
		int i = 0;
		while (i < LIMIT + 1) {
			denominators[i] = (int) decimal;
			decimal = 1.0 / (decimal - denominators[i]);
			i = i + 1;
		}
		int last = 0;
		while (last < LIMIT) {
			numerator = 1;
			denominator = 1;
			temp = 0;
			int current = last;
			while (current >= 0) {
				denominator = numerator;
				numerator = (numerator * denominators[current]) + temp;
				temp = denominator;
				current = current - 1;
			}
			last = last + 1;
			int goodness = denominators[last];
			if (Math.abs(goodness) > MAX_GOODNESS) break;
		}
		return denominator;
	}

    /* Converts each side of a chemical equation to an ArrayList of strings. */
	public static void converttoArrayList(String a, ArrayList<String> b){
		int pos = 0;
		for (int i = 0; i < a.length(); i++){
			if (i == a.length() - 1){
				String x = a.substring(pos, a.length());
				b.add(x);
			}
			if (Character.toString(a.charAt(i)).equals("+")){
				String x = a.substring(pos, i);
				pos = i + 1;
				b.add(x);
			}
		}
	}
	
	/* Configure compounds like NaCl which consist of one ion, by adding subscripts of 1.
	   e.g. NaCl -> Na1Cl1
	*/
	public static void addNums(ArrayList<String> b){
		for (int i = 0; i < b.size(); i++){
			String x = b.get(i);
			for (int j = 0; j < x.length() - 1; j++){
				if (!Character.isDigit(x.charAt(j)) && x.charAt(j + 1) == ')'){
					x = x.substring(0, j + 1) + "1" + x.substring(j + 1, x.length());
					break;
				}
				if ((Character.isUpperCase(x.charAt(j)) && !Character.isDigit(j + 1) && Character.isUpperCase(x.charAt(j + 1)))){
					x = x.substring(0, j + 1) + "1" + x.substring(j + 1, x.length());
				}
				else if (j == x.length() - 2 && Character.isUpperCase(x.charAt(j + 1))){
					x = x + "1";
				}
				if (Character.isUpperCase(x.charAt(j)) && Character.isLowerCase(x.charAt(j + 1))){
					if (j != x.length() - 2){
						if (Character.isUpperCase(x.charAt(j + 2)) || x.charAt(j + 2) == '('){
							x = x.substring(0, j + 2) + "1" + x.substring(j + 2, x.length());
						}
					}
					else if (j == x.length() - 2){
						x = x + "1";
					}
				}
			}
			b.set(i, x);
		}
	}
	
	/* Deals with compounds which have polyatomic ions (parentheses). */
	public static void configureParenthesis(ArrayList<String> b){
		int oldlength = 0;
		for (int i = 0; i < b.size(); i++){
			String x = b.get(i);
			oldlength = x.length();
			for (int j = 0; j < x.length() - 1; j++){
				if (x.charAt(j) == '('){
					int end = x.indexOf(')');
					Integer factor = Integer.parseInt(Character.toString(x.charAt(end + 1)));
					for (int k = j + 1; k < end; k++){
						if (Character.isDigit(x.charAt(k))){
							Integer num = Integer.parseInt(Character.toString(x.charAt(k)));
							Integer newnum = num * factor;
							String num1 = num.toString();
							int ind = x.indexOf(num1, k);
							x = x.substring(0, ind) + newnum.toString() + x.substring(ind + 1, x.length());
							if (x.length() > oldlength){
								k += x.length() - oldlength;
								end += x.length() - oldlength;
							}
							oldlength = x.length();
						}
					}
					if (j == 0){
						end = x.indexOf(')');
						x = x.substring(1, end) + x.substring(end + 2, x.length());
						b.set(i, x);
						break;
					}
					else
					{
						end = x.indexOf(')');
						x = x.substring(0, j) + x.substring(j + 1, end);
						b.set(i, x);
						break;
					}

				}
			}
		}
	}
	
	/* Computes the RREF (reduced row echelon form) of the matrix M through Gaussian row reduction. */
	public static void toRREF(double[][] M) {
		int rowCount = M.length;
		if (rowCount == 0)
			return;

		int columnCount = M[0].length;

		int lead = 0;
		for (int r = 0; r < rowCount; r++) {
			if (lead >= columnCount)
				break;
			{
				int i = r;
				while (M[i][lead] == 0) {
					i++;
					if (i == rowCount) {
						i = r;
						lead++;
						if (lead == columnCount)
							return;
					}
				}
				double[] temp = M[r];
				M[r] = M[i];
				M[i] = temp;
			}

			{
				double lv = M[r][lead];
				for (int j = 0; j < columnCount; j++)
					M[r][j] /= lv;
			}

			for (int i = 0; i < rowCount; i++) {
				if (i != r) {
					double lv = M[i][lead];
					for (int j = 0; j < columnCount; j++)
						M[i][j] -= lv * M[r][j];
				}
			}
			lead++;
		}
	}
	
	
	/**
	 * @param molePojoList
	 * @param moleProject
	 * @param sizeOfStringLength
	 * @param string
	 * @param tempString
	 * @param variable
	 * @param equalSign
	 * @param hm
	 * @throws NumberFormatException
	 */
	private void moleFactor(List<MoleElement> molePojoList,
			MoleElement moleProject, int sizeOfStringLength,
			String string, String tempString, char variable, String sign,
			Hashtable<String, Element> hm)
			throws NumberFormatException {
		 Map<String,Integer> map=new HashMap<String,Integer>();
		for (int beginIndex = 0; beginIndex < string.length(); beginIndex++) {
			   char ch = string.charAt(beginIndex);//this is the current index of character
			   if((sizeOfStringLength-1)>beginIndex){//this is for index check last index of string
			   char chNext = string.charAt(beginIndex+1);//this is for what is the next index of character
			   if(Character.isLetter(ch)&&(Character.isLetter(chNext))){
				   if(Character.isUpperCase(ch)&& (Character.isUpperCase(chNext))){
					  if(hm.get(String.valueOf(ch)) != null){
						if(map.containsKey(ch)){
							map.put(String.valueOf(ch), map.get(ch)+1);
						}else{
					   moleProject.setMoleName(String.valueOf(ch));
					   moleProject.setMoleQuantity(1);
					   moleProject.setMoleQuantitySign(sign);
					   moleProject.setMoleVariable(String.valueOf(variable));
					   molePojoList.add(moleProject);
					   map.put(String.valueOf(ch), 1);
					   moleProject=new MoleElement();
					   tempString =null;
						}
					  }
				   }else if(Character.isUpperCase(ch)&& (Character.isLowerCase(chNext))){
					   tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
				   }else if(Character.isLowerCase(ch)&& (Character.isUpperCase(chNext))){
					   String k=tempString+String.valueOf(ch);
					   if(hm.get(k) != null){
							if(map.containsKey(k)){
								map.put(k, map.get(k)+1);
							}else{
					   moleProject.setMoleName(k);
					   moleProject.setMoleQuantity(1);
					   moleProject.setMoleQuantitySign(sign);
					   moleProject.setMoleVariable(String.valueOf(variable));
					   molePojoList.add(moleProject);
					   map.put(k, 1);
					   moleProject=new MoleElement();
					   tempString=null;
						}
					   }
				   }else if(Character.isLowerCase(ch)&& (Character.isLowerCase(chNext))){
					   tempString=tempString+String.valueOf(ch);
				   }
				   
			   }else if(Character.isLetter(ch)&&(Character.isDigit(chNext))){
				   if(Character.isUpperCase(ch) && (Character.isDigit(chNext))){
					   tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
				   }else if(Character.isLetter(ch)&&(Character.isDigit(chNext))){
				   if(Character.isLowerCase(ch) && (Character.isDigit(chNext))){
					   tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
				   }
			   }}else if(Character.isDigit(ch)&&(Character.isLetter(chNext))){
				   if(Character.isDigit(ch)&& (Character.isUpperCase(chNext))){
					   if(hm.get(tempString) != null){
							if(map.containsKey(tempString)){
								map.put(tempString, map.get(tempString)+1);
							}else{
					   moleProject.setMoleName(tempString);
					   moleProject.setMoleQuantity(Integer.parseInt(String.valueOf(ch)));
					   moleProject.setMoleQuantitySign(sign);
					   moleProject.setMoleVariable(String.valueOf(variable));
					   molePojoList.add(moleProject);
					   map.put(tempString, Integer.parseInt(String.valueOf(ch)));
					   moleProject=new MoleElement();
					   tempString=null;
							}
					   }
				   }
			   }
			 }else{
				 if(Character.isLetter(ch)||(Character.isDigit(ch))){
					 if(Character.isLowerCase(ch)){
						 tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
						 if(hm.get(tempString) != null){
								if(map.containsKey(tempString)){
									map.put(tempString, map.get(tempString)+1);
								}else{
						   moleProject.setMoleName(tempString);
						   moleProject.setMoleQuantity(1);
						   moleProject.setMoleQuantitySign(sign);
						   moleProject.setMoleVariable(String.valueOf(variable));
						   molePojoList.add(moleProject);
						   map.put(tempString, 1);
						   moleProject=new MoleElement();
						   tempString=null;
								}
						 }
					 }else if(Character.isDigit(ch)){
						   if(hm.get(tempString) != null){
								if(map.containsKey(tempString)){
									map.put(tempString, map.get(tempString)+Integer.parseInt(String.valueOf(ch)));
								}else{
						   moleProject.setMoleQuantity(Integer.parseInt(String.valueOf(ch)));
						   moleProject.setMoleVariable(String.valueOf(variable));
						   moleProject.setMoleName(tempString);
						   moleProject.setMoleQuantitySign(sign);
						   molePojoList.add(moleProject);
						   map.put(tempString,Integer.parseInt(String.valueOf(ch)));
						   moleProject=new MoleElement();
						   tempString=null;
								}
						   }
					 }
					 else if(Character.isUpperCase(ch)){
						  tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
						  if(hm.get(tempString) != null){
								if(map.containsKey(tempString)){
									map.put(tempString, map.get(tempString)+1);
								}else{
						   moleProject.setMoleName(tempString);
						   moleProject.setMoleQuantity(1);
						   moleProject.setMoleVariable(String.valueOf(variable));
						   moleProject.setMoleQuantitySign(sign);
						   molePojoList.add(moleProject);
						   map.put(tempString,1);
						   moleProject=new MoleElement();
						   tempString=null;
								}
						  }
					 }
				 }
			 }
		 }
	}
	
	 private static Matrix convertMatrix(List<MoleElement> molePojoList ) {
		 List<String> map=new ArrayList<String>();
		 List<String> maptwo=new ArrayList<String>();
		 Map<String,String> mmm=new HashMap<String,String>();
		 for (MoleElement moleBreakingPojo : molePojoList) {
			 if(!map.contains(moleBreakingPojo.getMoleName())){
			 map.add(moleBreakingPojo.getMoleName());
			 }
			 if(!maptwo.contains(moleBreakingPojo.getMoleVariable())){
			 maptwo.add(moleBreakingPojo.getMoleVariable());
			 }
			 mmm.put(moleBreakingPojo.getMoleName()+"_"+moleBreakingPojo.getMoleVariable(), moleBreakingPojo.getMoleQuantitySign()+moleBreakingPojo.getMoleQuantity());
		 }
		 
		 for (String string : map) {
			for (String string2 : maptwo) {
				String data=string+"_"+string2;
				if(!mmm.containsKey(data)){
				 mmm.put(string+"_"+string2, "0");
				}
			}
		}
		 Collections.sort(map);
		 Collections.sort(maptwo);
	
		 
		 Matrix m = new Matrix(new double[map.size()][maptwo.size()]);
		 int i=0;
		 for (String string : map) {
			 int j=0;
			 for (String string2 : maptwo) {
				 String abc=string+"_"+string2;
				 m.set(j, i, Double.valueOf(mmm.get(abc)));
				j++;
			}
			i++;
		}
		 
		 for(int k=0;k<map.size();k++){
			 for(int l=0;l<maptwo.size();l++){
				 System.out.print(" "+m.get(k, l)+" ");
			 }
			 System.out.println();
				 
		 }
		 
		 return m;
		 
	 }
	 
	  public static void test(double[][] A, double[] b) {
	    	BalanceEquation gaussian = new BalanceEquation(A, b);
	        if (gaussian.isFeasible()) {
	            System.out.println("Solution to Ax = b");
	            double[] x = gaussian.primal();
	            for (int i = 0; i < x.length; i++) {
	                System.out.printf("%10.6f\n", x[i]);
	            }
	        }
	        else {
	            System.out.println("Certificate of infeasibility");
	            double[] y = gaussian.dual();
	            for (int j = 0; j < y.length; j++) {
	                System.out.printf("%10.6f\n", y[j]);
	            }
	        }
	        System.out.println();
	    }
	  
	  
	  
	  public static boolean  balanceGaussian(int x,int y,double [][] mtx) {
			int numReactant = x;
			int numProduct = y;
			
			if ((numReactant == 0) || (numProduct == 0)) {
				return false;
			}
			
			int cols = numReactant + numProduct;
			int lines = 0;
			
			{
				int[] pos = new int[lines];
				int rank = 0;
				for (int i = 0; rank < lines && i < cols; ++i) {
					if (mtx[rank][i] == 0) {
						int u = rank;
						while (u < lines && mtx[u][i] == 0)
							++u;
						if (u < lines)
							lnSwap(mtx[rank], mtx[u], i);
						else
							continue;
					}
					pos[rank] = i;
					lnSimplify(mtx[rank], i);
					for (int j = rank + 1; j < lines; ++j)
						if (mtx[j][i] != 0)
							lnSubstract(mtx[j], mtx[rank], i, i);
					++rank;
				}
				for (int i = 1; i < rank; ++i) {
					lnSimplify(mtx[i], pos[i]);
					for (int j = 0; j < i; ++j)
						lnSubstract(mtx[j], mtx[i], pos[j], pos[i]);
				}
				for (int i = 0; i < rank; ++i)
					lnSimplify(mtx[i], pos[i]);
			}
			
			{
				int numNonZero = 0;
				for (int j = 0; j < cols - 1; ++j) {
					for (int i = 0; i < lines; ++i) {
						if (mtx[i][j] != 0) {
							numNonZero++;
						}
					}
					if (numNonZero == 0 || numNonZero > 1) {
						return false;
					} else {
						numNonZero = 0;
						continue;
					}
				}
			}
			
			double[][] numResult = new double[cols-1][2];
			for (int j = 0; j < cols - 1; ++j) {
				int i;
				for (i = 0; i < lines; ++i) {
					if (mtx[i][j] != 0) {
						numResult[i][0] = mtx[i][j];
						break;
					}
				}
				numResult[i][1] = - mtx[i][cols-1];
			}
			
		
			double numGCD = numResult[0][0];
			for (int i = 0; i < cols - 1; ++i) {
				numGCD = numGCD*numResult[i][0]/gcd(numResult[i][0],numGCD);
			}
			
			double scale = 1;
			for (int i = 0; i < cols - 1; ++i) {
				scale = numGCD / numResult[i][0];
				numResult[i][0] = numGCD;
				numResult[i][1] *= scale;
				
				if (numResult[i][1] <= 0) {
					return false;
				}
			}
			return true;
		}
	
	  private static void lnSubstract(double[] ln1, double ln2[], int pos, int key) {
			assert((ln1[pos] != 0) && (ln2[pos] != 0));

			double d = gcd(ln1[key], ln2[key]);
			double a1 = Math.abs(ln2[key]) / d;
			double a2 = Math.abs(ln1[key]) / d;
			if (ln1[key] * ln2[key] > 0)
				for (int i = pos; i < ln1.length; ++i)
					ln1[i] = ln1[i] * a1 - ln2[i] * a2;
			else
				for (int i = pos; i < ln1.length; ++i)
					ln1[i] = ln1[i] * a1 + ln2[i] * a2;
		}
		
		private static void lnSimplify(double[] ln, int pos) {
			assert(ln[pos] != 0);

			double d = lnGcd(ln, pos);
			if (d > 1)
				for (int i = pos; i < ln.length; ++i)
					ln[i] /= d;
			if (ln[pos] < 0)
				for (int i = pos; i < ln.length; ++i)
					ln[i] = -ln[i];
		}
		
		private static double lnGcd(double[] ln, int pos) {
			double d = 0.0;
			for (int i = pos; d != 1 && i < ln.length; ++i)
				d = gcd(d, ln[i]);
			return d;
		}
		
		private static void lnSwap(double[] ln1, double[] ln2, int pos) {
			for (int i = pos; i < ln1.length; ++i) {
				double tmp = ln1[i];
				ln1[i] = ln2[i];
				ln2[i] = tmp;
			}
		}
		
		private static double gcd(double a, double b) {
			if (a < 0)
				a = -a;
			if (b < 0)
				b = -b;
			while (b != 0) {
				double c = a % b;
				a = b;
				b = c;
			}
			return a;
		}
	public static void main(String args[]){
		String data="NaOH+H2SO4=Na2SO4+H2O";
		System.out.println("Without solved equation "+data);
		Balance b=new Balance();
		System.out.println("With solved equation "+b.equationBalance(data));
	}
}
