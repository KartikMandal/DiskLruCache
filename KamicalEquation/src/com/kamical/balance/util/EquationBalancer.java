/*
 * Copyright (c) 2018
 * kamical india pvt Ltd.
 * village+PO saharda,Ps Pingla
 * Dist Paschim Medinipur
 * State WestBengal
 * Pin 721131
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of the the copyright holder nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package com.kamical.balance.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kamical.balance.model.MoleElement;

public class EquationBalancer {
	/**
	 * @purpose Primary method that balances an input chemical equation.
	 *<p>Examples:
	 * <ul>
	 *   <li><code>[C<sub>5</sub>H<sub>5</sub>]-</code></li>
	 *   <li><code>C<sub>6</sub>H<sub>6</sub></code></li>
	 *   <li><code><sup>12</sup>C<sub>5</sub><sup>13</sup>CH<sub>6</sub></code></li>
	 * </ul>
	 * @param equation
	 * @return String or null
	 * @author kartik.cse43 &lt;kartik.cse43@gmail.com&gt;
	 */
	public String equationBalance(String equation) {
		// initialize reactant and product ArrayLists
		ArrayList<String> rterms = new ArrayList<String>();
		ArrayList<String> pterms = new ArrayList<String>();
		// used for redox equations
		ArrayList<Double> coeffsr = new ArrayList<Double>();
		ArrayList<Double> coeffsp = new ArrayList<Double>();
		// enclose all code in a large try/catch to catch all possible errors in
		// equation
		try {
			// split equation into reactants and products, and convert to arrays
			// of compounds
			// String sign=UtilityMethod.extractReactionSeperator(equation);
			String reactants = equation.substring(0, equation.indexOf("="));
			String products = equation.substring(equation.indexOf("=") + 1,
					equation.length());
			reactants = reactants.trim();
			products = products.trim();
			UtilityMethod.converttoArrayList(reactants, rterms);
			UtilityMethod.converttoArrayList(products, pterms);
			String rterms1[] = new String[rterms.size()];
			String pterms1[] = new String[pterms.size()];
			for (int i = 0; i < rterms.size(); i++) {
				if (Character.isDigit(rterms.get(i).charAt(0))) {
					throw new Exception();
				}
				rterms.set(i, rterms.get(i).trim());
				rterms1[i] = rterms.get(i);
			}
			for (int i = 0; i < pterms.size(); i++) {
				if (Character.isDigit(pterms.get(i).charAt(0))) {
					throw new Exception();
				}
				pterms.set(i, pterms.get(i).trim());
				pterms1[i] = pterms.get(i);
			}

			// deal with redox equations
			boolean isredox = UtilityMethod.configureRedox(rterms, coeffsr);
			isredox = UtilityMethod.configureRedox(pterms, coeffsp);

			// add "1" subscripts and deal with polyatomic ions
			UtilityMethod.addNums(rterms);
			UtilityMethod.configureParenthesis(rterms);
			UtilityMethod.addNums(pterms);
			UtilityMethod.configureParenthesis(pterms);
			UtilityMethod.addNums(rterms);
			UtilityMethod.addNums(pterms);

			// equation is now all configured, do pattern matching to extract
			// elements
			ArrayList<String> elements = new ArrayList<String>();
			patternMatch(rterms, elements, rterms);
			patternMatch(pterms, elements, pterms);

			// initialize matrix m in order to solve system of equations
			int size = rterms.size() + pterms.size();
			double matrix[][] = null;
			int rows = elements.size();
			if (size > rows) {
				matrix = new double[size][size];
			} else if (rows > size) {
				matrix = new double[rows][size];
			} else if (rows == size) {
				matrix = new double[size][rows];
			}
			if (isredox) {
				matrix = new double[matrix.length + 1][matrix[0].length];
				int c = 0;
				for (int i = 0; i < coeffsr.size(); i++) {
					matrix[matrix.length - 1][c] = coeffsr.get(i);
					c++;
				}
				for (int i = 0; i < coeffsp.size(); i++) {
					if (i == coeffsp.size() - 1) {
						matrix[matrix.length - 1][c] = coeffsp.get(i);
					} else {
						matrix[matrix.length - 1][c] = coeffsp.get(i) * -1;
						c++;
					}
				}
			}

			// add system of equations to matrix m
			char variableSign = 'a';
			List<MoleElement> molePojoList = new ArrayList<MoleElement>();
			for (String string : rterms) {
				
				UtilityMethod.getMoleElementList(string, "+",molePojoList,String.valueOf(variableSign));
				
				/*addToMatrix(string, matrix, h, Constant.BEFORE, molePojoList,
						moleProject, variableSign);*/
				if (variableSign <= 'z') {
					++variableSign;
				}
			}
			for (String string : pterms) {
				/*addToMatrix(string, matrix, h, Constant.AFTER, molePojoList,
						moleProject, variableSign);*/
				UtilityMethod.getMoleElementList(string, "-",molePojoList,String.valueOf(variableSign));
				if (variableSign <= 'z') {
					++variableSign;
				}
			}
			MatrixClass m2 = convertMatrix(molePojoList, rterms.size(),
					pterms.size());
			// perform RREF to solve system of linear equations
			matrix = m2.content;
			int[] fin = gauseJordonElimination(matrix);

			// generate final equation
			String newequ1 = generateFinalEquation(rterms1, pterms1, fin);
			String newequ2 = UtilityMethod.removeSpaces(newequ1);
			return newequ2;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param matrix
	 * @return int value
	 * <code>
	 * https://www.nayuki.io/page/gauss-jordan-elimination-over-any-field
	 * </code>
	 */
	private int[] gauseJordonElimination(double[][] matrix) {
		int rowLength = matrix.length;
		int colLength = matrix[0].length;

		Field<Fraction> f = RationalField.FIELD;
		Matrix<Fraction> mat = new Matrix<>(rowLength, colLength, f);

		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < colLength; j++) {
				mat.set(i, j, new Fraction((int) matrix[i][j], 1));
			}
		}
		// Gauss-Jordan elimination
		mat.reducedRowEchelonForm();
		for (int i = 0; i < mat.rowCount(); i++) {
			for (int j = 0; j < mat.columnCount(); j++) {
				if (j > 0)
					System.out.print(" ");
				System.out.print(mat.get(i, j));
			}
			System.out.println();
		}
		int denoms[] = new int[colLength];

		for (int i = 0; i < colLength; i++) {
			Fraction data = mat.get(i, colLength - 1);
			denoms[i] = data.denominator.intValue();
		}
		long factor = 0;
		factor = UtilityMethod.lcm(denoms);
		int fin[] = new int[colLength];
		Double elem[] = new Double[colLength];
		for (int i = 0; i < colLength; i++) {
			Fraction data = mat.get(i, colLength - 1);
			if (data.numerator.intValue() == 0) {
				elem[i] = (double) ((factor * 1) / data.denominator.intValue());
			} else {
				elem[i] = (double) ((factor * data.numerator.intValue()) / data.denominator
						.intValue());
			}
			fin[i] = (int) Math.abs(Math.round(elem[i].doubleValue()));
		}
		return fin;
	}

	/**
	 * @purpose Generate final balanced equation from solved linear system of
	 *          equations.
	 * @param rterms1
	 * @param pterms1
	 * @param fin
	 * @return
	 */
	private String generateFinalEquation(String[] rterms1, String[] pterms1,
			int[] fin) {
		int cou = 0;
		// String newequ = "";
		String newequ1 = "";
		for (int i = 0; i < rterms1.length; i++) {
			if (fin[cou] == 1) {
				if (i == rterms1.length - 1) {
					// newequ += rterms1[i] + " \u2192 ";
					newequ1 += rterms1[i] + " = ";
				} else {
					// newequ += rterms1[i] + " + ";
					newequ1 += rterms1[i] + " + ";
				}
				cou++;
			} else {
				if (i == rterms1.length - 1) {
					// newequ += fin[cou] + rterms1[i] + " \u2192 ";
					newequ1 += fin[cou] + rterms1[i] + " = ";
				} else {
					// newequ += fin[cou] + rterms1[i] + " + ";
					newequ1 += fin[cou] + rterms1[i] + " + ";
				}
				cou++;
			}
		}
		for (int i = 0; i < pterms1.length; i++) {
			if (fin[cou] == 1) {
				if (i == pterms1.length - 1) {
					// newequ += pterms1[i];
					newequ1 += pterms1[i];
				} else {
					// newequ += pterms1[i] + " + ";
					newequ1 += pterms1[i] + " + ";
				}
				cou++;
			} else {
				if (i == pterms1.length - 1) {
					// newequ += fin[cou] + pterms1[i];
					newequ1 += fin[cou] + pterms1[i];
				} else {
					// newequ += fin[cou] + pterms1[i] + " + ";
					newequ1 += fin[cou] + pterms1[i] + " + ";
				}
				cou++;
			}
		}
		return newequ1;
	}

	/**
	 * @purpose Perform pattern matching to extract elements into output
	 *          ArrayList.
	 * @param terms
	 * @param elements
	 * @param rterms
	 */
	private void patternMatch(ArrayList<String> terms,
			ArrayList<String> elements, ArrayList<String> rterms) {
		for (int i = 0; i < terms.size(); i++) {
			// match single uppercase letter, or uppercase with 1 or 2 lowercase
			Pattern p1 = Pattern.compile("([A-Z])(\\d+)");
			String comp = rterms.get(i);
			Matcher m1 = p1.matcher(comp);
			while (m1.find()) {
				if (!elements.contains(m1.group(1))) {
					elements.add(m1.group(1));
				}
			}
			Pattern p2 = Pattern.compile("([A-Z])([a-z])(\\d+)");
			Matcher m2 = p2.matcher(comp);
			while (m2.find()) {
				if (!elements.contains(m2.group(1) + "" + m2.group(2))) {
					elements.add(m2.group(1) + "" + m2.group(2));
				}
			}
			Pattern p3 = Pattern.compile("([A-Z])([a-z])([a-z])(\\d+)");
			Matcher m3 = p3.matcher(comp);
			while (m3.find()) {
				if (!elements.contains(m3.group(1) + "" + m3.group(2) + ""
						+ m3.group(3))) {
					elements.add(m3.group(1) + "" + m3.group(2) + ""
							+ m3.group(3));
				}
			}
			if (rterms.get(i).length() == 1) {
				String e = rterms.get(i);
				rterms.set(i, e + "1");
			}
		}

	}

	

	/**
	 * 
	 * @param molePojoList
	 * @return
	 */

	private static MatrixClass convertMatrix(List<MoleElement> molePojoList,
			int leftCount, int rightCount) {
		List<String> map = new ArrayList<String>();
		List<String> maptwo = new ArrayList<String>();
		Map<String, String> mmm = new HashMap<String, String>();
		for (MoleElement moleBreakingPojo : molePojoList) {
			if (!map.contains(moleBreakingPojo.getMoleName())) {
				map.add(moleBreakingPojo.getMoleName());
			}
			if (!maptwo.contains(moleBreakingPojo.getMoleVariable())) {
				maptwo.add(moleBreakingPojo.getMoleVariable());
			}
			mmm.put(moleBreakingPojo.getMoleName() + "_"
					+ moleBreakingPojo.getMoleVariable(),
					moleBreakingPojo.getMoleQuantitySign()
							+ moleBreakingPojo.getMoleQuantity());
		}

		for (String string : map) {
			for (String string2 : maptwo) {
				String data = string + "_" + string2;
				if (!mmm.containsKey(data)) {
					mmm.put(string + "_" + string2, "0");
				}
			}
		}
		Collections.sort(map);
		Collections.sort(maptwo);

		MatrixClass m = null;
		if (leftCount == rightCount) {
			m = new MatrixClass(new double[map.size()][maptwo.size()]);
		} else {
			if (maptwo.size() > map.size()) {
				m = new MatrixClass(new double[maptwo.size()][maptwo.size()]);
			} else if (maptwo.size() < map.size()) {
				m = new MatrixClass(new double[map.size()][map.size()]);
			} else {
				m = new MatrixClass(new double[map.size()][maptwo.size()]);
			}
		}
		int i = 0;
		int r = 0;
		for (String string : map) {
			int j = 0;
			for (String string2 : maptwo) {
				String abc = string + "_" + string2;
				m.set(j, i, Double.valueOf(mmm.get(abc)));// this is the vital
															// place of i and j
				j++;
			}
			i++;
			r = j;
		}
		if (leftCount != rightCount) {
			if (map.size() > maptwo.size()) {
				for (int p = 0; p < map.size(); p++) {
					for (int q = r; q < map.size(); q++) {
						m.set(q, p, Double.valueOf(0));
					}

				}
			} else if (map.size() < maptwo.size()) {
				for (int p = i; p < maptwo.size(); p++) {
					for (int j = 0; j < maptwo.size(); j++) {
						m.set(j, p, Double.valueOf(0));
					}

				}
			}
		}
		UtilityMethod.printMatrix(m.content);

		return m;

	}

	

	public static void main(String args[]) {
		String[] abc = {
				"NaOH+H2SO4=Na2SO4+H2O",
				"NaOH+HCl=NaCl+H20",
				"H2+O2=H2O",
				"H2O=H2+O2",
				"KMnO4 + HCl = KCl + MnCl2 + H2O + Cl2",
				"FeCl2+Na3PO4=Fe3(PO4)2+NaCl",
				"K4Fe(CN)6 + H2SO4 + H2O = K2SO4 + FeSO4 + (NH4)2SO4 + CO",
				"K4Fe(CN)6 + KMnO4 + H2SO4 = KHSO4 + Fe2(SO4)3 + MnSO4 + HNO3 + CO2 + H2O",
				"HCl+Na2O=NaCl+H2O",
				"AgBr + Na2S2O3 = Na3[Ag(S2O3)2] + NaBr",
				"K3[Fe(SCN)6] + Na2Cr2O7 + H2SO4 = Fe(NO3)3 + Cr2(SO4)3 + CO2 + H2O + Na2SO4 + KNO3",
				"K4[Fe(SCN)6] + K2Cr2O7 + H2SO4 =Fe2(SO4)3 + Cr2(SO4)3 + CO2 + H2O + K2SO4 + KNO3",
				"Au + KCN + O2 + H2O = K[Au(CN)2] + KOH",
				"Ca10F2(PO4)6 + H2SO4 = Ca(H2PO4)2 + CaSO4 + HF",
				"Ca5F(PO4)3 + H2SO4 = Ca(H2PO4)2 + CaSO4 + HF",
				"Pb3(VO4)2.PbCl2 + HCl = VO2Cl + PbCl2 + H2O"};
		/*
		 * String [] abc={"AgBr + Na2S2O3 = Na3[Ag(S2O3)2] + NaBr",
		 * "K3[Fe(SCN)6] + Na2Cr2O7 + H2SO4 = Fe(NO3)3 + Cr2(SO4)3 + CO2 + H2O + Na2SO4 + KNO3"
		 * };
		 */
		UtilityMethod.clearConsole();
		for (String data : abc) {
			System.out.println();
			System.out.println("Without solved the equation " + data);
			EquationBalancer b = new EquationBalancer();
			System.out.println("With solved the equation "
					+ b.equationBalance(data));
			System.out
					.println("---------------------------------------------------------------");

		}

	}
}
