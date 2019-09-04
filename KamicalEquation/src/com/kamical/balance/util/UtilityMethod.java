package com.kamical.balance.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kamical.balance.model.Element;
import com.kamical.balance.model.MoleElement;
import com.kamical.balance.model.PeriodicTable;

public class UtilityMethod<E> {

	/* Determine if an equation is a redox equation and add
	   oxidation/reduction coefficients to coeffs array.
	*/
	public static boolean configureRedox(ArrayList<String> terms, ArrayList<Double> coeffs) {
	    boolean isredox = false;
		for (int i = 0; i < terms.size(); i++){
			String x = terms.get(i);
			x =x.replace("[", "");
			x =x.replace("]", "");
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
	public static String removeSpaces(String s) {
		StringTokenizer st = new StringTokenizer(s, " ", false);
		String t = "";
		while (st.hasMoreElements()) t += st.nextElement();
		return t;
	}
	
	 //output
    public static void printMatrix(double mat[][]) {
    	int rowSize=mat.length;
    	int colSize=mat[0].length;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                System.out.printf("%.2f ", mat[i][j]);
            }
            System.out.println();
        }
        System.out.println();
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
	
	/**
	 * Configure compounds like NaCl which consist of one ion, by adding subscripts of 1. 
	 * e.g. NaCl -> Na1Cl1
	 * Add one space after the number
	 * @param b
	 */
	public static void addNums(ArrayList<String> b){
		for (int i = 0; i < b.size(); i++){
			String x = b.get(i);
			for (int j = 0; j < x.length() - 1; j++){
				if (!Character.isDigit(x.charAt(j)) && x.charAt(j + 1) == ')'){
					x = x.substring(0, j + 1) + "1 " + x.substring(j + 1, x.length());
					break;
				}
				if ((Character.isUpperCase(x.charAt(j)) && !Character.isDigit(j + 1) && Character.isUpperCase(x.charAt(j + 1)))){
					x = x.substring(0, j + 1) + "1 " + x.substring(j + 1, x.length());
				}else if(Character.isDigit(x.charAt(j)) && !Character.isDigit(x.charAt(j+1)) && Character.isUpperCase(x.charAt(j + 1))){
					x = x.substring(0, j + 1) + " " + x.substring(j + 1, x.length());
				}
				else if (j == x.length() - 2 && Character.isUpperCase(x.charAt(j + 1))){
					x = x + "1 ";
				}
				
				
				if (Character.isUpperCase(x.charAt(j)) && Character.isLowerCase(x.charAt(j + 1))){
					if (j != x.length() - 2){
						if (Character.isUpperCase(x.charAt(j + 2)) || x.charAt(j + 2) == '('){
							x = x.substring(0, j + 2) + "1 " + x.substring(j + 2, x.length());
						}
					}
					else if (j == x.length() - 2){
						x = x + "1 ";
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
	
	// ## operation extractReactionSeperator(String)
    public static final String extractReactionSeperator(String p_string) {
        if (p_string == null)
            throw new NullPointerException();
        /*StringTokenizer st = new StringTokenizer(p_string);
        while (st.hasMoreTokens()) {
            String s = st.nextToken().trim();
            if (s.equals("<=>"))
                return "<=>";
            else if (s.equals("="))
                return "=";
            else if (s.equals("=>"))
                return "=>";
            else if (s.equals("->"))
                return "->";
        }*/
        String regex = "^[=->=><=>]{1}$";
        
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(p_string);
        while (matcher.find())
        {
         /*System.out.print("Start index: " + matcher.start());
         System.out.print(" End index: " + matcher.end() + " ");
         System.out.println(" : " + matcher.group());*/
         return matcher.group();
        }
        return null;
    }
    
    public static double[][] conMatrix(String data){
        String[] rows = data.split("|");
        double[][] matrix = new double[rows.length][]; 
        for (String row : rows) {
        	int i = 0;
        	String[] cols = row.split(",");
        	for (String col : cols) {
        		int j=0;
        		 matrix[i][j] = Double.parseDouble(col);
        	}
        }
    	
		return matrix;
    	
    }
    /**
     * 
     */
    public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}
    /**
     * 
     * @param string
     * @return
     * @throws NumberFormatException
     */
    public static List<MoleElement> getMoleElementList(String string)
			throws NumberFormatException {
    	return getMoleElementList(string,null,null,null);
    }
    /**
     * 
     * @param string
     * @param sign
     * @param molePojoList
     * @param variableSign
     * @return
     * @throws NumberFormatException
     */
	public static List<MoleElement> getMoleElementList(String string,
			String sign, List<MoleElement> molePojoList, String variableSign)
			throws NumberFormatException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		MoleElement moleProject = new MoleElement();
		LinkedHashMap<String, Element> hm = PeriodicTable
				.getPeriodicTableAtomNameMap();
		if(null == molePojoList){
			molePojoList=new ArrayList<MoleElement>();
		}
		String[] splited = string.split(" ");
		for (String str : splited) {
			// String str="abcd1243";
			String[] part = str.split("(?<=\\D)(?=\\d)");
			// System.out.println(part[0]);
			// System.out.println(part[1]);
			if (map.containsKey(part[0])) {
				int sum = (int) map.get(part[0]) + Integer.parseInt(part[1]);
				map.put(part[0], sum);
				for (MoleElement moleElement : molePojoList) {
					if (moleElement.getMoleName().equals(part[0])) {
						moleElement.setMoleQuantity(sum);
					}
				}

			} else {
				map.put(part[0], Integer.parseInt(part[1]));
				moleProject.setMoleName(part[0]);
				moleProject.setMoleQuantitySign(sign);
				moleProject.setMoleQuantity(Integer.parseInt(part[1]));
				Element e = hm.get(part[0]);
				moleProject.setMoleVariable(variableSign);// this is like a,b,c
															// d upto z etc
				moleProject.setAtomValency(e.getDefaultAtomValancy());
				molePojoList.add(moleProject);
				moleProject = new MoleElement();
			}

		}
		return molePojoList;
	}
	
	
	public static List<MoleElement> getParseElement(String string)
			throws NumberFormatException {
		//Map<String, Integer> map = new HashMap<String, Integer>();
		LinkedHashMap<String, Element> hm = PeriodicTable
				.getPeriodicTableAtomNameMap();
		
		String[] splited = string.split(" ");
		for (String str : splited) {
			String[] part = str.split("(?<=\\D)(?=\\d)");
			// System.out.println(part[0]);
			// System.out.println(part[1]);
			
		}
		return null;
	}
}
