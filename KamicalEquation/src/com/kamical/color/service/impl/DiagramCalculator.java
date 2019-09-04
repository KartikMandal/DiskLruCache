package com.kamical.color.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import com.kamical.balance.model.Element;
import com.kamical.balance.model.MoleElement;
import com.kamical.balance.model.PeriodicTable;
import com.kamical.balance.util.UtilityMethod;
import com.kamical.desgin.model.ElectronConfiguration;

public class DiagramCalculator {
	
	/**
	 * <pre>
	 * S.No 	Steric factor 	Bond pair electrons 	Lone pair electrons Electronic structure 	Molecular structure 		Example 
	 * -----------------------------------------------------------------------------------------------------------------------------------------------------
	 *  1 		2 			2 		- 		linear 			linear 			BeCl2 
	 *  2 		3 			3 		- 		trigonal 			trigonal 			BF3 
	 *  3 		4 			4 		- 		tetrahedral 		tetrahedral 		CH4 
	 *  4 		4 			3 		1 		tetrahedral 		pyramidal 		NH3 
	 *  5 		4 			2 		2 		tetrahedral 		bend shape 		H2O 
	 *  6 		5 			5 		- 		trigonal Bi pyramidal 	trigonal bipyramidal	PCl5 
	 *  7 		5 			4 		1 		trigonal bipyramidal 	seesaw 			SF4 
	 *  8 		5 			3 		2 		trigonal bipyramidal 	T shape 			ClF3 
	 *  9 		6 			6 		- 		Octahedral 		Octahedral 		SF6 
	 *  10		6 			5 		1 		octahedral		square pyramidal 		XeOF4 

	 * 
	 * http://www.meta-synthesis.com/webbook/45_vsepr/VSEPR.html
	 * https://chemistry.tutorvista.com/organic-chemistry/vsepr-model.html
	 * https://gist.github.com/gilleain/1870655
	 * 
	 * https://www.youtube.com/watch?v=OBi3cZaI_K0   -->>Search this word  Chemistry - Chemical Bonding (2 of 35) Lewis Structures
	 * https://www.youtube.com/watch?v=ZrKpB4ZcsKE  -->>Search this word  Chemistry - Chemical Bonding (8 of 35) Lewis Structures
	 * https://www.slideshare.net/TimothyWelsh/molecular-geometry-cheat-sheet 
	 * https://www.khanacademy.org/science/biology/chemistry--of-life/chemical-bonds-and-reactions/v/ionic-covalent-and-metallic-bonds
	 * https://chemistry.tutorvista.com/organic-chemistry/vsepr-model.html
	 * http://www.meta-synthesis.com/webbook/45_vsepr/VSEPR.html
	 * Step 0: We need to figure out what type of compound
	 * Step 0 1: Name the metal (the cation) as it appers on the peroid table.
	 * Step 0 2:Find the polyatomic ion on the common ion table and write the name.
	 * Step 1: Find out the total number of valence electrons.
	 * Step 2: Convert to  pair electrons.
	 * Step 3: Put the least electro negative atom in center.
	 * Step 4: Put two electron in atoms to form a chemical bond.
	 * Step 5: Complete octets on the out side atoms.
	 * Step 6: If central atoms does not have an octet, and move electrons from outer atoms to forms double or triple bonds.
	 * 
	 * Step 3 I: The atom with fewer valance electron form the centerl structure like (C,N,Al,Sc etc)
	 * Step 3 II: Highly electronegative tend to cluster around central atom
	 * Step 3 III: moluclues perefer a compact structrure rather than  chain structure 
	 * Step 3 IV: avoid a seperation of formal charges.
	 * Step 3 V: Find out polarized(C-O   here Oxigen have more electronegative from Carbon)  or non-polarized(C-C  both are carbon so both have same electronegative) situations because  have 
	 * </code>
	 * </pre>
	 * @param moles
	 */
	public String diagramGenerator(String equation) {
		// initialize reactant and product ArrayLists
		ArrayList<String> rterms = new ArrayList<String>();
		// used for redox equations
		ArrayList<Double> coeffsr = new ArrayList<Double>();
		// enclose all code in a large try/catch to catch all possible errors in
		// equation
		try {
			String reactants = null;
			reactants = equation.trim();
			reactants =reactants.replace("+", "");
			reactants =reactants.replace("-", "");
			UtilityMethod.converttoArrayList(reactants, rterms);
			String rterms1[] = new String[rterms.size()];
			for (int i = 0; i < rterms.size(); i++) {
				if (Character.isDigit(rterms.get(i).charAt(0))) {
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
			int totalElectron=0;
			List<MoleElement> moleElementList = null;
			for (String string : rterms) {
				/*String str = "abcd1234";
				String[] part = str.split("(?<=\\D)(?=\\d)");
				System.out.println(part[0]);
				System.out.println(part[1]);*/
				
				totalElectron = totalNumberOfValency(string);
				moleElementList = UtilityMethod.getMoleElementList(string);
				
			}
			
			int totalElectronPair=totalElectron/2;
			System.out.println("Get the total electron "+totalElectron+" and Electron pair "+totalElectronPair);
			
			
			LinkedHashMap<String, Element> hm = PeriodicTable.getPeriodicTableAtomNameMap();
			List<Element> list = new ArrayList<Element>();
			List<Element> temp = new ArrayList<Element>();
			for (MoleElement element : moleElementList) {
				Element e=hm.get(element.getMoleName());
				if(!e.getMoleSymbol().equals("H")){
				list.add(e);
				}
				temp.add(e);
			}
			
			Collections.sort(list, new Comparator<Object>() {
				public int compare(Object obj1, Object obj2) {
					Element e1 = (Element) obj1;
					Element e2 = (Element) obj2;
					return Float.compare(e1.getElectroNegativity(), e2.getElectroNegativity());
				}
			});
			
			//Find out the least electronegative atom in center.
			String centralAtom= null;
			int numberOfCentralAtom= 0;
			for (Element element : list) {
				if(null == centralAtom){
					centralAtom = element.getMoleSymbol();
					for (MoleElement element2 : moleElementList) {
						if(centralAtom.equals(element2.getMoleName())){
							numberOfCentralAtom= element2.getMoleQuantity();
						}
					}
				}
				System.out.println(element.getMoleName()+" Number of central atom -->>"+numberOfCentralAtom);
				//numberOfCentralAtom =0;
			}
			
			List<Element> sideAtom=new ArrayList<Element>();
			for (Element string : temp) {
				if(!centralAtom.equals(string.getMoleSymbol())){
					sideAtom.add(string);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return equation;
	}
	
	public static int totalNumberOfValency(String string){
		List<MoleElement> molePojoList = UtilityMethod.getMoleElementList(string);
		int sum=0;
		for (MoleElement moleElement : molePojoList) {
			sum=sum+moleElement.getMoleQuantity()*Math.abs(ElectronConfiguration.getValanceElenctron(moleElement.getMoleName()));
		}
		
		return sum;
		
		
	}

		
	public static void main(String args[]) {
		String[] abc = {"CH3CH2NO2","C12H22O11","C6H5NH2","C6H12O6"};
		/*
		 * String [] abc={"AgBr + Na2S2O3 = Na3[Ag(S2O3)2] + NaBr",
		 * "K3[Fe(SCN)6] + Na2Cr2O7 + H2SO4 = Fe(NO3)3 + Cr2(SO4)3 + CO2 + H2O + Na2SO4 + KNO3"
		 * };
		 */
		
		UtilityMethod.clearConsole();
		for (String data : abc) {
			System.out.println();
			System.out.println("Without solved the equation " + data);
			DiagramCalculator b = new DiagramCalculator();
			System.out.println("With solved the equation "
					+ b.diagramGenerator(data));
			System.out
					.println("---------------------------------------------------------------");

		}

	}
}
