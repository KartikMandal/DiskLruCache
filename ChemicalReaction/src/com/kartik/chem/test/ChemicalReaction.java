package com.kartik.chem.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChemicalReaction {
	/**
	 * 
	 * @param s
	 */
	public void reaction(String s){
		 String[] temp;
		 String delimiter = "[\\s+]+";
		 temp = s.split(delimiter);
		// "NiCl2+HCl+(NH4)2S";
		 
		 
		 Map<String,ArrayList<Formula>> tempList=null;
		 List<Map<String,ArrayList<Formula>>> mapList=new ArrayList<Map<String,ArrayList<Formula>>>();
		 int count=0;//this is required for How many map put the data
		 ArrayList<Formula> molePojoList=null;
		 Formula moleProject=null;
		 int sizeOfStringLength=0;
		 for (String string : temp) {
			 int moleIndex=1;
			 String tempString=null;
			 molePojoList=new ArrayList<Formula>();
			 moleProject=new Formula();
			 sizeOfStringLength=string.length();//this is the total length of String
			 mapSetting(molePojoList, moleProject, sizeOfStringLength, string,
					moleIndex, tempString);
			 tempList=new HashMap<String,ArrayList<Formula>>();
			 tempList.put(String.valueOf(count), molePojoList);
			 mapList.add(tempList);
			 count++;
		 }
		 
		 Object [] mapArray =new Object[mapList.size()];
		 Object [] listArray =new Object[mapList.size()];
		 
		 for (Map<String,ArrayList<Formula>> map : mapList) {
			 chemicalValency(map);
		}
		 for(int i=0;i<mapList.size();i++){
			 mapArray[i]=mapList.get(i);
		 }
		  
		 
		 System.out.println("After Reaction----->>>");
		 StringBuffer sb=new StringBuffer();
		 sb.append("=");
		 for(int i=0;i<mapList.size();i++){
			Map<String,ArrayList<Formula>> map=(Map<String, ArrayList<Formula>>) mapArray[i];
			 for (Object keyValue : map.keySet()) {
				 listArray[i]=map.get(keyValue);
			 }
		 }
		 
		 for(int i=0;i<mapList.size();i++){
		 if(listArray[i]!=null){
			 for (Formula molePojo : (ArrayList<Formula>) listArray[i]) {
				 if(i<mapList.size()-1){
				 for (Formula molePojoTwo : (ArrayList<Formula>) listArray[i+1]) {
					 if(i<mapList.size()-2){
						 for (Formula molePojoThree : (ArrayList<Formula>) listArray[i+2]) {
							 if(molePojo.getMoleSign()=='+' && molePojoTwo.getMoleSign()=='+' && molePojoThree.getMoleSign()=='-'){
								 if((molePojo.getMoleName().equals("H")||molePojoTwo.getMoleName().equals("H")) && (molePojoThree.getMoleName().equals("O") || molePojoThree.getMoleName().equals("OH"))){
										sb.append("H2O");
										sb.append("+");
									}else{
										sb.append(newValencyAndQuantity(molePojo.getMoleName(),molePojoTwo.getMoleName(),molePojoThree.getMoleName()));
										sb.append("+");
								     }
							 }else if(molePojo.getMoleSign()=='+' && molePojoTwo.getMoleSign()=='-' && molePojoThree.getMoleSign()=='+'){
								 if((molePojo.getMoleName().equals("H")||molePojoThree.getMoleName().equals("H")) && (molePojoTwo.getMoleName().equals("O") || molePojoTwo.getMoleName().equals("OH"))){
										sb.append("H2O");
										sb.append("+");
									}else{
										sb.append(newValencyAndQuantity(molePojo.getMoleName(),molePojoTwo.getMoleName(),molePojoThree.getMoleName()));
										sb.append("+");
								     }
							 }else if(molePojo.getMoleSign()=='-' && molePojoTwo.getMoleSign()=='+' && molePojoThree.getMoleSign()=='+'){
								 if((molePojoTwo.getMoleName().equals("H")||molePojoThree.getMoleName().equals("H")) && (molePojo.getMoleName().equals("O") || molePojo.getMoleName().equals("OH"))){
										sb.append("H2O");
										sb.append("+");
									}else{
										sb.append(newValencyAndQuantity(molePojo.getMoleName(),molePojoTwo.getMoleName(),molePojoThree.getMoleName()));
										sb.append("+");
								     }
							 }
						 }
					 }else{
					 
					 if(molePojo.getMoleSign()=='+' && molePojoTwo.getMoleSign()=='-'){
							if(molePojo.getMoleName().equals("H") && (molePojoTwo.getMoleName().equals("O") || molePojoTwo.getMoleName().equals("OH"))){
								sb.append("H2O");
								sb.append("+");
							}else{
								sb.append(newValencyAndQuantity(molePojo.getMoleName(),molePojoTwo.getMoleName(),null));
								sb.append("+");
						     }
						}else if(molePojo.getMoleSign()=='-' && molePojoTwo.getMoleSign()=='+'){
							if(molePojoTwo.getMoleName().equals("H") && (molePojo.getMoleName().equals("O") || molePojo.getMoleName().equals("OH"))){
								sb.append("H2O");
								sb.append("+");
							}else{
							sb.append(newValencyAndQuantity(molePojoTwo.getMoleName(),molePojo.getMoleName(),null));
							sb.append("+");
									}
								}
							}
						}
					}
				}
			}
		}
		 System.out.println(s+sb.toString().substring(0,sb.toString().length()-1));
		 
				 
		 /*if(listOne!=null){
			 for (Object keyValue : listOne.keySet()) {
				    ArrayList<MolePojo> listData=new ArrayList<MolePojo>();
				    listData=listOne.get(keyValue);
				    for (MolePojo molePojo : listData) {
						 if(listTwo!=null){
							 for (Object keyValueTwo : listTwo.keySet()) {
								    ArrayList<MolePojo> listDataTwo=new ArrayList<MolePojo>();
								    listDataTwo=listTwo.get(keyValueTwo);
								    for (MolePojo molePojoTwo : listDataTwo) {
										if(molePojo.getMoleSign()=='+' && molePojoTwo.getMoleSign()=='-'){
											if(molePojo.getMoleName().equals("H") && (molePojoTwo.getMoleName().equals("O") || molePojoTwo.getMoleName().equals("OH"))){
												sb.append("H2O");
												sb.append("+");
											}else{
												sb.append(newValencyAndQuantity(molePojo.getMoleName(),molePojoTwo.getMoleName(),null));
												sb.append("+");
										     }
										}else if(molePojo.getMoleSign()=='-' && molePojoTwo.getMoleSign()=='+'){
											if(molePojoTwo.getMoleName().equals("H") && (molePojo.getMoleName().equals("O") || molePojo.getMoleName().equals("OH"))){
												sb.append("H2O");
												sb.append("+");
											}else{
											sb.append(newValencyAndQuantity(molePojoTwo.getMoleName(),molePojo.getMoleName(),null));
											sb.append("+");
										  }
										}
										
									}
								}
						 }
					}
				    
				    System.out.println(s+sb.toString().substring(0,sb.toString().length()-1));
				}
		 }*/ 
    }

	/**
	 * @param molePojoList
	 * @param molePoject
	 * @param sizeOfStringLength
	 * @param string
	 * @param moleIndex
	 * @param tempString
	 * @throws NumberFormatException
	 */
	private void mapSetting(ArrayList<Formula> molePojoList,
			Formula moleProject, int sizeOfStringLength, String string,
			int moleIndex, String tempString) throws NumberFormatException {
		for (int beginIndex = 0; beginIndex < string.length(); beginIndex++) {
			   char ch = string.charAt(beginIndex);//this is the current index of character
			   if((sizeOfStringLength-1)>beginIndex){//this is for index check last index of string
			   char chNext = string.charAt(beginIndex+1);//this is for what is the next index of character
			   if(Character.isLetter(ch)&&(Character.isLetter(chNext))){
				   if(Character.isUpperCase(ch)&& (Character.isUpperCase(chNext))){
					   if(ch=='O'||chNext=='O'){
						   tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
					   }else{
					   moleProject.setMoleId(moleIndex);
					   moleProject.setMoleName(String.valueOf(ch));
					   moleProject.setMoleQuantity(1);
					   molePojoList.add(moleProject);
					   moleProject=new Formula();
					   moleIndex++;
					   }
				   }else if(Character.isUpperCase(ch)&& (Character.isLowerCase(chNext))){
					   tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
				   }else if(Character.isLowerCase(ch)&& (Character.isUpperCase(chNext))){
					   moleProject.setMoleId(moleIndex);
					   moleProject.setMoleName(tempString+String.valueOf(ch));
					   moleProject.setMoleQuantity(1);
					   molePojoList.add(moleProject);
					   moleProject=new Formula();
					   tempString=null;
					   moleIndex++;
					   
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
					   moleProject.setMoleId(moleIndex);
					   moleProject.setMoleName(tempString);
					   moleProject.setMoleQuantity(Integer.parseInt(String.valueOf(ch)));
					   molePojoList.add(moleProject);
					   moleProject=new Formula();
					   tempString=null;
					   moleIndex++;
				   }
			   }
			 }else{
				 if(Character.isLetter(ch)||(Character.isDigit(ch))){
					 if(Character.isLowerCase(ch)){
						 moleProject.setMoleId(moleIndex);
						 tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
						   moleProject.setMoleName(tempString);
						   moleProject.setMoleQuantity(1);
						   molePojoList.add(moleProject);
						   moleProject=new Formula();
						   tempString=null;
						   moleIndex++;
					 }else if(Character.isDigit(ch)){
						  moleProject.setMoleId(moleIndex);
						  if((tempString.length()==2) && (tempString.startsWith("O")||tempString.endsWith("O"))){
							  tempString=tempString+String.valueOf(ch);
							  moleProject.setMoleQuantity(1);
						  }else{
							  moleProject.setMoleQuantity(Integer.parseInt(String.valueOf(ch)));
						  }
						   moleProject.setMoleName(tempString);
						   molePojoList.add(moleProject);
						   tempString=null;
						   moleIndex++;
					 }
					 else if(Character.isUpperCase(ch)){
						  moleProject.setMoleId(moleIndex);
						  tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
						   moleProject.setMoleName(tempString);
						   moleProject.setMoleQuantity(1);
						   molePojoList.add(moleProject);
						   tempString=null;
						   moleIndex++;
					 }
				 }
			 }
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
	private void mapSettingForBalanceCreateEquation(ArrayList<Formula> molePojoList,
			Formula moleProject, int sizeOfStringLength, String string,
			int moleIndex, String tempString) throws NumberFormatException {
		
		/*char c;

	    for(c = 'a'; c <= 'z'; ++c){
	      System.out.println(c); 
	    }*/
		//KOH
		//Ca(OCl)Cl
		for (int beginIndex = 0; beginIndex < string.length(); beginIndex++) {
			   char ch = string.charAt(beginIndex);//this is the current index of character
			   if((sizeOfStringLength-1)>beginIndex){//this is for index check last index of string
			   char chNext = string.charAt(beginIndex+1);//this is for what is the next index of character
			   if(Character.isLetter(ch)&&(Character.isLetter(chNext))){
				   if(Character.isUpperCase(ch)&& (Character.isUpperCase(chNext))){
					   if(ch=='O'||chNext=='O'){
						   tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
					   }else{
					   moleProject.setMoleId(moleIndex);
					   moleProject.setMoleName(String.valueOf(ch));
					   moleProject.setMoleQuantity(1);
					   molePojoList.add(moleProject);
					   moleProject=new Formula();
					   moleIndex++;
					   }
				   }else if(Character.isUpperCase(ch)&& (Character.isLowerCase(chNext))){
					   tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
				   }else if(Character.isLowerCase(ch)&& (Character.isUpperCase(chNext))){
					   moleProject.setMoleId(moleIndex);
					   moleProject.setMoleName(tempString+String.valueOf(ch));
					   moleProject.setMoleQuantity(1);
					   molePojoList.add(moleProject);
					   moleProject=new Formula();
					   tempString=null;
					   moleIndex++;
					   
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
					   moleProject.setMoleId(moleIndex);
					   moleProject.setMoleName(tempString);
					   moleProject.setMoleQuantity(Integer.parseInt(String.valueOf(ch)));
					   molePojoList.add(moleProject);
					   moleProject=new Formula();
					   tempString=null;
					   moleIndex++;
				   }
			   }
			 }else{
				 if(Character.isLetter(ch)||(Character.isDigit(ch))){
					 if(Character.isLowerCase(ch)){
						 moleProject.setMoleId(moleIndex);
						 tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
						   moleProject.setMoleName(tempString);
						   moleProject.setMoleQuantity(1);
						   molePojoList.add(moleProject);
						   moleProject=new Formula();
						   tempString=null;
						   moleIndex++;
					 }else if(Character.isDigit(ch)){
						  moleProject.setMoleId(moleIndex);
						  if((tempString.length()==2) && (tempString.startsWith("O")||tempString.endsWith("O"))){
							  tempString=tempString+String.valueOf(ch);
							  moleProject.setMoleQuantity(1);
						  }else{
							  moleProject.setMoleQuantity(Integer.parseInt(String.valueOf(ch)));
						  }
						   moleProject.setMoleName(tempString);
						   molePojoList.add(moleProject);
						   tempString=null;
						   moleIndex++;
					 }
					 else if(Character.isUpperCase(ch)){
						  moleProject.setMoleId(moleIndex);
						  tempString = (tempString!=null) ? tempString+String.valueOf(ch): String.valueOf(ch);
						   moleProject.setMoleName(tempString);
						   moleProject.setMoleQuantity(1);
						   molePojoList.add(moleProject);
						   tempString=null;
						   moleIndex++;
					 }
				 }
			 }
		 }
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	
	public String chemicalValency(Map<String,ArrayList<Formula>> data){
		 if(data!=null){
			 int tempMoleQnt=0;
			 List<Map<String,Integer>> listOfKeyValancyPair=new ArrayList<Map<String,Integer>>();
			 PeriodicTable constantElement=new PeriodicTable();
		     constantElement.setInitialValues();
			 Hashtable<String,Element> hm=constantElement.getHm();
			 
			 for (Object keyValue : data.keySet()) {
				    ArrayList<Formula> listData=new ArrayList<Formula>();
				    listData=data.get(keyValue);
				    Map<String,Integer> keyValancyPair = null;
				   for (Formula molePojo : listData) {
				    	Set<String> keys = hm.keySet();
				        for(String key: keys){
				        	if(key.equalsIgnoreCase(molePojo.getMoleName().trim())){
				        		tempMoleQnt=(molePojo.getMoleQuantity()==1)?1:molePojo.getMoleQuantity();
				        		keyValancyPair = new HashMap<String,Integer>();
				        		keyValancyPair.put(key, tempMoleQnt);
				        		listOfKeyValancyPair.add(keyValancyPair);
				        	}
				        }
				        
					System.out.println(molePojo.getMoleId()+"-"+molePojo.getMoleName()+"-"+molePojo.getMoleQuantity());
					}
			 }
			 
			 		int[] stringArryOfQuantity =new int[listOfKeyValancyPair.size()];
			       
			        List<Set<String>> listOfValancyStringArray=new ArrayList<Set<String>>();
			        for (int i = 0; i < listOfKeyValancyPair.size(); i++) {
			        	Map<String,Integer> keyValancyPairMap=listOfKeyValancyPair.get(i);
			        	for (String string : keyValancyPairMap.keySet()) {
			        		stringArryOfQuantity[i]=keyValancyPairMap.get(string);
			        		listOfValancyStringArray.add(new HashSet<String>(Arrays.asList(hm.get(string).getValency())));
						}
					}
			   
			        

 			        Set<String> oneSet =null,twoSet =null,threeSet =null;
			        
			        for (Set<String> string : listOfValancyStringArray) {
			        	if(oneSet==null){
			        		oneSet =string;
				        }
			        	else if(twoSet==null){
			        		twoSet =string;
				        }else if(threeSet==null){
				        	threeSet =string;
				        }
					}
			        
			        
			        for (Object keyValue : data.keySet()) {
					    ArrayList<Formula> listData=new ArrayList<Formula>();
					    listData=data.get(keyValue);
			        sumOfOldElementValancy(stringArryOfQuantity, oneSet,
							twoSet, threeSet, listData);
			        }
		 }
		return null;
	}

	/**
	 * @param stringArryOfQuantity
	 * @param oneSet
	 * @param twoSet
	 * @param threeSet
	 * @param listData
	 * @throws NumberFormatException
	 */
	private void sumOfOldElementValancy(int[] stringArryOfQuantity,
			Set<String> oneSet, Set<String> twoSet, Set<String> threeSet,
			ArrayList<Formula> listData) throws NumberFormatException {
		int sum;
		if(oneSet!=null && twoSet!=null && threeSet!=null ){
		    for (String string : oneSet) {
		    	sum=-1;
		    	sum=(stringArryOfQuantity[0]*Integer.parseInt(string));
		    	int tempSum=-1;
		    	tempSum=sum;
		    	for (String stringTwo : twoSet) {
		    		sum=tempSum;
		    		sum=sum+(stringArryOfQuantity[1]*Integer.parseInt(stringTwo));
		    		int temSumTwo=-1;
		    		temSumTwo=sum;
		    			for (String stringThree : threeSet) {
		    				sum=temSumTwo;
		    				sum=sum+(stringArryOfQuantity[2]*Integer.parseInt(stringThree));
		    		if(sum==0){
		    			int count=0;
		    			for (Formula molePojo : listData) {
		    				if(count==0){
		    				char sign=(string.charAt(0)=='+')?'+':'-';
		    				molePojo.setMoleSign(sign);
		    				molePojo.setMoleValency(string);
		    				System.out.println(" Mole Name--->> "+molePojo.getMoleName()+" Valency-->> "+string);
		    				count++;
		    				}
		    				else if(count==1){
		        				char sign=(stringTwo.charAt(0)=='+')?'+':'-';
		        				molePojo.setMoleSign(sign);
		        				molePojo.setMoleValency(stringTwo);
		        				System.out.println(" Mole Name--->> "+molePojo.getMoleName()+" Valency-->> "+stringTwo);
		        				count++;
		        			}else if(count==2){
		        				char sign=(stringThree.charAt(0)=='+')?'+':'-';
		        				molePojo.setMoleSign(sign);
		        				molePojo.setMoleValency(stringThree);
		        				System.out.println(" Mole Name--->> "+molePojo.getMoleName()+" Valency-->> "+stringThree);
		        				count++;
		        			}
		    				
		    			}
		    			count=0;
		    		}
		    		sum=temSumTwo;
		    	  }
		    	}
		    	sum=tempSum;
			}
		    }else if(oneSet!=null && twoSet!=null && threeSet==null){
		for (String string : oneSet) {
			sum=-1;
			sum=(stringArryOfQuantity[0]*Integer.parseInt(string));
			int tempSum=sum;
			for (String stringTwo : twoSet) {
				if(stringTwo!=null){
					sum=sum+(stringArryOfQuantity[1]*Integer.parseInt(stringTwo));
				if(sum==0){
					int count=0;
					for (Formula molePojo : listData) {
						if(count==0){
						char sign=(string.charAt(0)=='+')?'+':'-';
						molePojo.setMoleSign(sign);
						molePojo.setMoleValency(string);
						System.out.println(" Mole Name--->> "+molePojo.getMoleName()+" Valency-->> "+string);
						count++;
						}
						else if(count==1){
		    				char sign=(stringTwo.charAt(0)=='+')?'+':'-';
		    				molePojo.setMoleSign(sign);
		    				molePojo.setMoleValency(stringTwo);
		    				System.out.println(" Mole Name--->> "+molePojo.getMoleName()+" Valency-->> "+stringTwo);
		    				count++;
		    			}
					}
					count=0;
				}
				sum=tempSum;
			}
		  }
		}
		}else if(oneSet!=null && twoSet==null && threeSet==null){
			for (String string : oneSet) {
				for (Formula molePojo : listData) {
				char sign=(string.charAt(0)=='+')?'+':'-';
				molePojo.setMoleSign(sign);
				molePojo.setMoleValency(string);
				System.out.println(" Mole Name--->> "+molePojo.getMoleName()+" Valency-->> "+string);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param moleFirst
	 * @param moleSecond
	 * @param moleThird
	 * @return
	 */
	public String newValencyAndQuantity(String moleFirst,String moleSecond,String moleThird){
	        Element oneMole=null;
	        Element oneTwo=null;
	        Element oneThree=null;
	        String defaultValencyOne=null,defaultValencytwo=null,defaultValencyThree=null,defaultValencyFour=null;
	        PeriodicTable constantElement=new PeriodicTable();
	    	constantElement.setInitialValues();
	        Hashtable<String,Element> hm=constantElement.getHm();
	        if(moleFirst!=null){
	        oneMole=hm.get(moleFirst);
	        defaultValencyOne=oneMole.getDefaultValancy();
	        }
	        if(moleSecond!=null){
	        oneTwo=hm.get(moleSecond);
	        defaultValencytwo=oneTwo.getDefaultValancy();
	        }
	        if(moleThird!=null){
	         oneThree=hm.get(moleThird);
	         defaultValencyThree=oneThree.getDefaultValancy();
	        }
	        int quty=0;
	        int sum=0;
	        StringBuffer ab=new StringBuffer();
	        if(defaultValencyOne!=null && defaultValencytwo!=null){
	        	sum=Integer.parseInt(defaultValencyOne)+Integer.parseInt(defaultValencytwo);
	        	if(sum==0){
	        		ab.append(moleFirst).append(moleSecond);
	        		return ab.toString();
	        	}else if(sum<0){
	        		quty=Integer.parseInt(defaultValencytwo.substring(1))/Integer.parseInt(defaultValencyOne.substring(1));
	        		ab.append(moleFirst).append(quty).append(moleSecond);
	        		return ab.toString();
	        	}else if(sum>0){
	        		quty=Integer.parseInt(defaultValencyOne.substring(1))/Integer.parseInt(defaultValencytwo.substring(1));
	        		ab.append(moleFirst).append(moleSecond).append(quty);
	        		return ab.toString();
	        	}
	        }
			return null;
       }
   }




