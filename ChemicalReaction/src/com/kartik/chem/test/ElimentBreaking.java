package com.kartik.chem.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class ElimentBreaking {
	/**
	 * 
	 * @param s
	 */
	public void breaking(String s){
		// String[] temp;
		// String delimiter = "[\\s+]+";
		// temp = s.split(delimiter);
		// "NiCl2+HCl+(NH4)2S";
		Map<String, BeforeAfterElement> map=new HashMap<String, BeforeAfterElement>();
		BeforeAfterElement bf1=new BeforeAfterElement();
		bf1.setEqualSign("Before");
		bf1.setData("NaOH");
		map.put("NaOH", bf1);
		
		BeforeAfterElement bf2=new BeforeAfterElement();
		bf2.setEqualSign("Before");
		bf2.setData("H2SO4");
		map.put("H2SO4", bf2);
		
		BeforeAfterElement bf3=new BeforeAfterElement();
		bf3.setEqualSign("After");
		bf3.setData("Na2SO4");
		map.put("Na2SO4", bf3);
		
		BeforeAfterElement bf4=new BeforeAfterElement();
		bf4.setEqualSign("After");
		bf4.setData("H2O");
		map.put("H2O", bf4);
		List<MoleElement> molePojoList =new ArrayList<MoleElement>();
		 	MoleElement moleProject =null;
		 	char c = 'a';
		    	int sizeOfStringLength=0;
		    	for (Entry<String, BeforeAfterElement> moleBreakingPojo : map.entrySet()) {
					 String tempString=null;
					 moleProject=new MoleElement();
					 BeforeAfterElement bf=moleBreakingPojo.getValue();
					 String string = bf.getData();
					 sizeOfStringLength=string.length();
					 mapSettingForBalanceCreateEquation(molePojoList, moleProject, sizeOfStringLength, string, tempString,c,bf.getEqualSign());
					 if(c <= 'z'){
						 ++c;
					 }
				 }
					 convertMatrix(map.size(),molePojoList);
				 
		    }
	
	
	
	/**
	 *
<pre>
<b>Balancing of Chemical Equations using Matrix Algebra:</b>
The chemical equation is;
NaOH + H2SO4 --> Na2SO4 + H2O [Not Balanced]
To balance this equation, we insert unknowns, multiplying the reactants and the products to get an equation of
the form
wNaOH + xH2SO4 ---> yNa2SO4 + zH2O
Next, we compare the number of sodium (Na), oxygen (O), hydrogen (H), and sulfur (S) atoms of the reactants
with the number of the products. We obtain four linear equations
Na: w = 2y
O: w + 4 x = 4y + z
H: w + 2 x = 2z
S:  x = y
It

It is important to note that we made use of the subscripts because they count the number of atoms of a particular
element. Rewriting these equations in standard form, we see that we have a homogenous linear system in four
unknowns, that is, w,x,y,z

1w + 0x -2y +0Z=0
1w + 4x -4y -1z=0
1w + 2x +0y -2z=0
0w + 1x -1y -0z=0

Alter matrix form
 \	w	x	 y	 z
  \_____________________________________________________
H |	1	2	 0	-2	|0
Na|	1	0	-2	 0	|0
O |	1	4	-4	-1	|0
S |	0	1	-1	 0	|0

Next use <b>Gauss-Jordan Elimination Method</b> and get the solution
</pre>
	 */
	 private static void convertMatrix(int s,List<MoleElement> molePojoList ) {
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
		 
	 }
	
	/**
	 * 
	 * @param molePojoList
	 * @param moleProject
	 * @param sizeOfStringLength
	 * @param string
	 * @param moleIndex
	 * @param tempString
	 * @throws NumberFormatException
	 */
	private void mapSettingForBalanceCreateEquation(List<MoleElement> molePojoList,
			MoleElement moleProject, int sizeOfStringLength, String string, String tempString,char variable,String equalSign) throws NumberFormatException {
		PeriodicTable constantElement=new PeriodicTable();
	     constantElement.setInitialValues();
		 Hashtable<String,Element> hm=constantElement.getHm();
		//KOH
		//Ca(OCl)Cl
		 moleFactor(molePojoList, moleProject, sizeOfStringLength, string,
				tempString, variable, equalSign, hm);
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
			String string, String tempString, char variable, String equalSign,
			Hashtable<String, Element> hm)
			throws NumberFormatException {
		String sign=(equalSign.equalsIgnoreCase("Before")?"+":"-");
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
						//moleProject(String.valueOf(variable),String.valueOf(ch),1);
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
						 //  moleProject(String.valueOf(variable),tempString,1);
						   molePojoList.add(moleProject);
						   map.put(tempString, 1);
						   moleProject=new MoleElement();
						   tempString=null;
								}
						 }
					 }else if(Character.isDigit(ch)){
						   tempString=tempString+String.valueOf(ch);
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

	

}
