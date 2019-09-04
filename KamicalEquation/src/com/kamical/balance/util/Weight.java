package com.kamical.balance.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.kamical.balance.model.Element;
import com.kamical.balance.model.MoleElement;
import com.kamical.balance.model.PeriodicTable;

public class Weight {
	public String getMoleWeight(String equation) {
	    // initialize reactant and product ArrayLists
		ArrayList<String> rterms = new ArrayList<String>();
		// used for redox equations
		ArrayList<Double> coeffsr = new ArrayList<Double>();
		// enclose all code in a large try/catch to catch all possible errors in equation
		try {
		    // split equation into reactants and products, and convert to arrays of compounds
			UtilityMethod.converttoArrayList(equation.trim(), rterms);
			String rterms1[] = new String[rterms.size()];
			for (int i = 0; i < rterms.size(); i++){
				if (Character.isDigit(rterms.get(i).charAt(0))){
					throw new Exception();
				}
				rterms.set(i, rterms.get(i).trim());
				rterms1[i] = rterms.get(i);
			}
			
			
			// deal with redox equations
			 UtilityMethod.configureRedox(rterms, coeffsr);
			
			// add "1" subscripts and deal with polyatomic ions
			UtilityMethod.addNums(rterms);
			UtilityMethod.configureParenthesis(rterms);
		
			UtilityMethod.addNums(rterms);
			LinkedHashMap<String,Element> hm=PeriodicTable.getPeriodicTableAtomNameMap();
	     	List<MoleElement> molePojoList =null;
	     	molePojoList =UtilityMethod.getMoleElementList(rterms.get(0));
	     	double weight=0.0;
	     	for (MoleElement moleElement : molePojoList) {
	     		String mole=moleElement.getMoleName();
	     		Element e=hm.get(mole);
	     		System.out.println(e.getWeight());
	     		weight+=(e.getWeight()*moleElement.getMoleQuantity());
				
			}
	     	
	     	return String.valueOf(weight);
	     	
		}catch(Exception e){
			
		}
		return null;
	}
	
	
		
	public static void main(String args[]){
		String data="H2O";
		System.out.println("Without solved equation "+data);
		Weight b=new Weight();
		System.out.println(b.getMoleWeight(data));
	}
}
