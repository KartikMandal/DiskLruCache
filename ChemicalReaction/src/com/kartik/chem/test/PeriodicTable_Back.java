/*
* Copyright (c) 1997 - 2016
* Actelion Pharmaceuticals Ltd.
* Gewerbestrasse 16
* CH-4123 Allschwil, Switzerland
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

package com.kartik.chem.test;

import java.util.Hashtable;


public class PeriodicTable_Back {

	public static final int ConnectionPoint = 0;

	public static final int Hydrogen = 1;

	public static final int Carbon = 6;
	
	public static final int Nitrogen = 7;
	
	public static final int Oxygen = 8;
	
	public static final int Fluorine = 9;
	
	public static final int Silicon = 14;
	
	public static final int Phosphorus = 15;
	
	public static final int Sulfur = 16;
	
	public static final int Chlorine = 17;
	
	public static final int Bromine = 35;
	
	public static final int Iodine = 53;
	
	
	
	private static Element_Back[] arrData;

	private Hashtable<Integer, Element_Back> htblDataAtNo;

	private Hashtable<String, Element_Back> htblDataName;

	private Hashtable<String, Element_Back> htblDataSymbol;

	private static PeriodicTable_Back tbl;

	private PeriodicTable_Back() {

		htblDataAtNo = new Hashtable<Integer, Element_Back>();

		htblDataName = new Hashtable<String, Element_Back>();

		htblDataSymbol = new Hashtable<String, Element_Back>();

// Order Number, Element Symbol, Atomic Weight, Covalent Radius, VdW radius
    arrData = new Element_Back [] {
			new Element_Back(1,"Hydrogen","H",1.008,0.435,1.185,2.200),
			new Element_Back(2,"Helium","He",4.003,0.930,1.785,0.000),
			new Element_Back(3,"Lithium","Li",6.940,1.520,0.000,0.980),
			new Element_Back(4,"Beryllium","Be",9.013,1.143,0.000,1.570),
			new Element_Back(5,"Boron","B",10.820,0.975,1.700,2.040),
			new Element_Back(6,"Carbon","C",12.011,0.655,1.750,2.550),
			new Element_Back(7,"Nitrogen","N",14.008,0.750,1.525,3.040),
			new Element_Back(8,"Oxygen","O",16.000,0.730,1.400,3.440),
			new Element_Back(9,"Fluorine","F",19.000,0.720,1.350,3.980),
			new Element_Back(10,"Neon","Ne",20.183,0.710,1.600,0.000),
			new Element_Back(11,"Sodium","Na",22.991,1.858,0.000,0.930),
			new Element_Back(12,"Magnesium","Mg",24.320,1.605,0.000,1.310),
			new Element_Back(13,"Aluminum","Al",26.980,1.432,0.000,1.610),
			new Element_Back(14,"Silicon","Si",28.090,1.176,2.000,1.900),
			new Element_Back(15,"Phosphorus","P",30.975,1.060,1.900,2.190),
			new Element_Back(16,"Sulfur","S",32.066,1.020,1.850,2.580),
			new Element_Back(17,"Chlorine","Cl",35.457,0.990,1.780,3.160),
			new Element_Back(18,"Argon","Ar",39.944,0.980,1.900,0.000),
			new Element_Back(19,"Potassium","K",39.100,2.262,0.000,1.000),
			new Element_Back(20,"Calcium","Ca",40.080,1.976,0.000,1.360),
			new Element_Back(21,"Scandium","Sc",44.960,1.655,0.000,1.540),
			new Element_Back(22,"Titanium","Ti",47.900,1.476,0.000,1.630),
			new Element_Back(23,"Vanadium","V",50.950,1.309,0.000,1.660),
			new Element_Back(24,"Chromium","Cr",52.010,1.249,0.000,1.550),
			new Element_Back(25,"Manganese","Mn",54.940,1.350,0.000,1.830),
			new Element_Back(26,"Iron","Fe",55.850,1.241,0.000,1.880),
			new Element_Back(27,"Cobalt","Co",58.940,1.254,0.000,1.910),
			new Element_Back(28,"Nickel","Ni",58.690,1.246,0.000,1.900),
			new Element_Back(29,"Copper","Cu",63.540,1.278,0.000,1.650),
			new Element_Back(30,"Zinc","Zn",65.380,1.333,0.000,1.810),
			new Element_Back(31,"Gallium","Ga",69.720,1.350,0.000,2.010),
			new Element_Back(32,"Germanium","Ge",72.600,1.225,0.000,0.000),
			new Element_Back(33,"Arsenic","As",74.910,1.200,2.100,2.180),
			new Element_Back(34,"Selenium","Se",78.960,1.160,2.000,2.550),
			new Element_Back(35,"Bromine","Br",79.916,1.140,1.970,0.000),
			new Element_Back(36,"Krypton","Kr",83.800,1.120,2.000,2.960),
			new Element_Back(37,"Rubidium","Rb",85.480,2.470,0.000,0.000),
			new Element_Back(38,"Strontium","Sr",87.630,2.151,0.000,0.820),
			new Element_Back(39,"Yttrium","Y",88.920,1.824,0.000,0.950),
			new Element_Back(40,"Zirconium","Zr",91.220,1.616,0.000,1.220),
			new Element_Back(41,"Niobium","Nb",92.910,1.432,0.000,1.330),
			new Element_Back(42,"Molybdenum","Mo",95.950,1.363,0.000,1.600),
			new Element_Back(43,"Technetium","Tc",99.000,1.367,0.000,2.160),
			new Element_Back(44,"Ruthenium","Ru",101.100,1.353,0.000,1.900),
			new Element_Back(45,"Rhodium","Rh",102.910,1.345,0.000,2.200),
			new Element_Back(46,"Palladium","Pd",106.700,1.375,0.000,2.280),
			new Element_Back(47,"Silver","Ag",107.880,1.445,0.000,2.200),
			new Element_Back(48,"Cadmium","Cd",112.410,1.489,0.000,1.930),
			new Element_Back(49,"Indium","In",114.760,1.666,0.000,1.690),
			new Element_Back(50,"Tin","Sn",118.700,1.538,0.000,1.780),
			new Element_Back(51,"Antimony","Sb",121.760,1.400,2.200,1.960),
			new Element_Back(52,"Tellurium","Te",127.610,1.360,2.200,0.000),
			new Element_Back(53,"Iodine","I",126.910,1.330,2.200,2.050),
			new Element_Back(54,"Xenon","Xe",131.300,1.310,2.200,2.100),
			new Element_Back(55,"Cesium","Cs",132.910,2.632,0.000,2.660),
			new Element_Back(56,"Barium","Ba",137.360,2.171,0.000,2.600),
			new Element_Back(57,"Lanthanum","La",138.920,1.873,0.000,0.790),
			new Element_Back(58,"Cerium","Ce",140.130,1.824,0.000,0.890),
			new Element_Back(59,"Praesodymium","Pr",140.920,1.836,0.000,1.100),
			new Element_Back(60,"Neodymium","Nd",144.270,1.829,0.000,1.120),
			new Element_Back(61,"Promethium","Pm",145.000,1.809,0.000,1.130),
			new Element_Back(62,"Samarium","Sm",150.430,1.804,0.000,1.140),
			new Element_Back(63,"Europium","Eu",152.000,1.984,0.000,0.000),
			new Element_Back(64,"Gadolinium","Gd",156.900,1.818,0.000,1.170),
			new Element_Back(65,"Terbium","Tb",158.930,1.800,0.000,0.000),
			new Element_Back(66,"Dyprosium","Dy",162.460,1.795,0.000,1.200),
			new Element_Back(67,"Holmium","Ho",164.940,1.789,0.000,0.000),
			new Element_Back(68,"Erbium","Er",167.200,1.779,0.000,1.220),
			new Element_Back(69,"Thulium","Tm",168.940,1.769,0.000,1.230),
			new Element_Back(70,"Ytterbium","Yb",173.040,1.940,0.000,1.240),
			new Element_Back(71,"Lutetium","Lu",174.990,1.752,0.000,1.250),
			new Element_Back(72,"Hafnium","Hf",178.600,1.597,0.000,0.000),
			new Element_Back(73,"Tantalium","Ta",180.950,1.428,0.000,1.270),
			new Element_Back(74,"Wolfram","W",183.920,1.371,0.000,1.300),
			new Element_Back(75,"Rhenium","Re",186.310,1.380,0.000,1.500),
			new Element_Back(76,"Osmium","Os",190.200,1.368,0.000,2.360),
			new Element_Back(77,"Iridium","Ir",192.200,1.357,0.000,1.900),
			new Element_Back(78,"Platinum","Pt",195.230,1.387,0.000,2.200),
			new Element_Back(79,"Gold","Au",197.000,1.442,0.000,2.200),
			new Element_Back(80,"Mercury","Hg",200.610,1.502,0.000,2.280),
			new Element_Back(81,"Thallium","Tl",204.390,1.728,0.000,2.540),
			new Element_Back(82,"Lead","Pb",207.210,1.750,0.000,2.000),
			new Element_Back(83,"Bismuth","Bi",209.000,1.460,0.000,1.620),
			new Element_Back(84,"Polonium","Po",210.000,1.460,0.000,2.330),
			new Element_Back(85,"Astatine","At",210.000,1.450,0.000,2.020),
			new Element_Back(86,"Radon","Rn",222.000,1.430,0.000,2.000),
			new Element_Back(87,"Francium","Fr",223.000,2.500,0.000,2.200),
			new Element_Back(88,"Radium","Ra",226.050,2.140,0.000,0.000),
			new Element_Back(89,"Actinium","Ac",227.000,1.877,0.000,0.700),
			new Element_Back(90,"Thorium","Th",232.050,1.798,0.000,0.890),
			new Element_Back(91,"Protactinium","Pa",231.000,1.609,0.000,1.100),
			new Element_Back(92,"Uranium","U",238.070,1.568,0.000,1.300),
			new Element_Back(93,"Neptunium","Np",237.000,0.000,0.000,1.500),
			new Element_Back(94,"Plutonium","Pu",242.000,0.000,0.000,1.380),
			new Element_Back(95,"Americium","Am",243.000,0.000,0.000,1.360),
			new Element_Back(96,"Curium","Cm",243.000,0.000,0.000,1.280),
			new Element_Back(97,"Berkelium","Bk",245.000,0.000,0.000,1.300),
			new Element_Back(98,"Californium","Cf",246.000,0.000,0.000,1.300),
			new Element_Back(99,"Einsteinium","E",-999.000,0.000,0.000,1.300),
			new Element_Back(100,"Fermium","Fm",-999.000,0.000,0.000,1.300),
			new Element_Back(101,"Mendelevium","Mv",-999.000,0.000,0.000,1.300)
    };

    for (int ii = 0; ii < arrData.length; ii++) {
			htblDataAtNo
					.put(new Integer(arrData[ii].getOrderNumber()), arrData[ii]);
			htblDataName.put(arrData[ii].getName(), arrData[ii]);
			htblDataSymbol.put(arrData[ii].getSymbol(), arrData[ii]);
		}
	}

	private static PeriodicTable_Back getInstance() {
		if (tbl == null) {
			tbl = new PeriodicTable_Back();
		}
		return tbl;
	}

	/**
	 * 
	 * @param sNameOrSymbol
	 * @return atomic order number or 0 if the input can not be resolved to an element.
	 */
	public static int number(String sNameOrSymbol) {

		int iOrderNumber = 0;

		Element_Back el = getInstance().htblDataName.get(sNameOrSymbol);
		if (el == null)
			el = getInstance().htblDataSymbol.get(sNameOrSymbol);

		if (el != null)
			iOrderNumber = el.getOrderNumber();

		return iOrderNumber;
	}

	public static String symbol(int iOrderNumber) {

		String sSymbol = "";

		Element_Back el = getInstance().htblDataAtNo.get(iOrderNumber);

		if (el != null)
			sSymbol = el.getSymbol();

		return sSymbol;
	}

	public static int size(){
		return getInstance().htblDataAtNo.size();
	}
	
	/**
	 * 
	 * @param iOrderNumber
	 * @return element weight
	 */
	public static double getWeight(int iOrderNumber) {

		Element_Back el = getInstance().htblDataAtNo.get(iOrderNumber);

		return el.getWeight();
	}

	public static String name(int iOrderNumber) {

		String sName = "";

		Element_Back el = getInstance().htblDataAtNo.get(iOrderNumber);

		if (el != null)
			sName = el.getName();

		return sName;
	}

	public static Element_Back getElement(int orderNumber) {
		return getInstance().htblDataAtNo.get(orderNumber);
	}
	
	public String toString() {
		String sPeriodicTable = "";
		return sPeriodicTable;
	}
}