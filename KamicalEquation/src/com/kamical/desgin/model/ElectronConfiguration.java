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
package com.kamical.desgin.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import com.kamical.balance.model.Element;
import com.kamical.balance.model.PeriodicTable;

/**
 * https://github.com/Ruegg/ElementConfiguration/blob/master/Java%20Source/src/
 * ruegg/andre/OrbitalDiagram.java
 * 
 * @author kmandal
 *
 */
public class ElectronConfiguration {

	//public static LinkedHashMap<String, Integer> orbital = new LinkedHashMap<String, Integer>();

	static String[] energyLevels = { "1s", "2s", "2p", "3s", "3p", "4s", "3d",
			"4p", "5s", "4d", "5p", "6s", "4f", "5d", "6p", "7s", "5f", "6d",
			"7p", "8s", "6f", "7d", "8p", "9s", "7f", "8d", "9p", "10s", "8f",
			"9d", "10p", "11s" };

	/**
	 * 
	 * @param atomicNumber
	 * @return
	 */
	public String getElectronConfiguration(int atomicNumber,String atomicName) {
		if(atomicNumber>118 || atomicNumber<1){
			throw new RuntimeException("Atomic number not valid");
		}
		LinkedHashMap<String, Integer> orbital = new LinkedHashMap<String, Integer>();
		int countElectron = 0;
		int remainingElectorn = atomicNumber;
		for (String string : energyLevels) {
			String block = string.substring(1);
			if (atomicNumber > countElectron) {
				int blockData = getOrbitalValue(block);
				if (remainingElectorn > blockData) {
					orbital.put(string, blockData);
					countElectron += blockData;
					remainingElectorn = atomicNumber - countElectron;
				} else {
					orbital.put(string, remainingElectorn);
					countElectron += remainingElectorn;
					remainingElectorn = atomicNumber - countElectron;
					break;
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		int count = 1;
		for (Entry<String, Integer> entry : orbital.entrySet()) {
			if (orbital.size() > count) {
				sb.append(entry.getKey() + entry.getValue()).append(',');
				count++;
			} else {
				sb.append(entry.getKey() + entry.getValue());
			}
		}
		sb2.append(sb.toString()).append("\n").append("Kartik Chandra Mandal");
		getElectronCircleImage(orbital, sb2.toString(),atomicName);
		return sb.toString();
	}

	
	/**
	 * 
	 * @param atomicNumber
	 * @return
	 */
	public static int getValanceElenctron(String atomicName) {
				
		int countElectron = 0;
		LinkedHashMap<String, Element> hm = PeriodicTable.getPeriodicTableAtomNameMap();
		LinkedHashMap<String, Integer> orbitalPath = new LinkedHashMap<String, Integer>();
		Element e=hm.get(atomicName);
		int atomicNumber=e.getPeriodicId();
		int remainingElectorn = atomicNumber;
		for (String string : energyLevels) {
			String block = string.substring(1);
			if (atomicNumber > countElectron) {
				int blockData = getOrbitalValue(block);
				if (remainingElectorn > blockData) {
					orbitalPath.put(string, blockData);
					countElectron += blockData;
					remainingElectorn = atomicNumber - countElectron;
				} else {
					orbitalPath.put(string, remainingElectorn);
					countElectron += remainingElectorn;
					remainingElectorn = atomicNumber - countElectron;
					break;
				}
			}
		}
		LinkedHashMap<String, Integer> orbit = new LinkedHashMap<String, Integer>();
		int elec = 0;
		for (Entry<String, Integer> entry : orbitalPath.entrySet()) {
			String dd = entry.getKey().substring(0, 1);
			if (orbit.containsKey(dd)) {
				elec = orbit.get(dd) + entry.getValue();
				orbit.put(dd, elec);
				elec = 0;
			} else {
				orbit.put(dd, entry.getValue());
			}
		}
		System.out.println("Valency electron of "+atomicName +"-->>"+orbit.get(String.valueOf(orbit.size())));
		return orbit.get(String.valueOf(orbit.size()));
	}
	/**
	 * 
	 * @param orbitalPath
	 * @return
	 */
	public static int getOrbitalValue(String orbitalPath) {
		switch (orbitalPath) {
		case "s":
			return 2;
		case "p":
			return 6;
		case "d":
			return 10;
		case "f":
			return 14;
		}
		return 0;
	}

	static File file = new File("D:\\chemical\\_Electron.png");

	/**
	 * 
	 * @param orb
	 * @param text
	 */
	public static void getElectronCircleImage(
			LinkedHashMap<String, Integer> orb, String text,String atomicName) {
		BufferedImage image = new BufferedImage(600, 600,
				BufferedImage.TYPE_BYTE_INDEXED);
		Graphics2D g2 = image.createGraphics();

		LinkedHashMap<String, Integer> orbit = new LinkedHashMap<String, Integer>();
		int elec = 0;
		for (Entry<String, Integer> entry : orb.entrySet()) {
			String dd = entry.getKey().substring(0, 1);
			if (orbit.containsKey(dd)) {
				elec = orbit.get(dd) + entry.getValue();
				orbit.put(dd, elec);
				elec = 0;
			} else {
				orbit.put(dd, entry.getValue());
			}
		}

		int x = 300, y = 300;
		double size = 70;
		int count = 0;
		double point = 1.6;
		for (Entry<String, Integer> entry : orbit.entrySet()) {
			if (count == 0) {
				drawCenteredFirstCircle(g2, x, y, 20,atomicName);
				count++;
			}
			drawCenteredCircle(g2, x, y, size, entry.getValue(), text);
			size = size * point;
			point = point - .1;
		}
		count = 0;
		g2.dispose();
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("done");

	}

	/**
	 * 
	 * @param g
	 * @param x
	 * @param y
	 * @param r
	 */
	public static void drawCenteredFirstCircle(Graphics2D g, int x, int y, int r,String atomicName) {
		x = x - (r / 2);
		y = y - (r / 2);
		g.setPaint(Color.GREEN);
		g.fillOval(x, y, r, r);
		g.drawString(atomicName, x, y);
	}

	/**
	 * 
	 * @param g
	 * @param x
	 * @param y
	 * @param r
	 * @param electron
	 * @param text
	 */
	public static void drawCenteredCircle(Graphics2D g, double x, double y,
			double r, int electron, String text) {
		double pointX = x, pointY = y, pointX1 = x, pointY1 = y;
		x = x - (r / 2);
		y = y - (r / 2);
		g.setPaint(Color.RED);
		Shape circle2 = new Ellipse2D.Double(x, y, r, r);
		g.draw(circle2);
		g.setPaint(Color.BLUE);
		double tempAngle = 360 / electron;
		double angle = 0.0;
		for (int i = 0; i < electron; i++) {
			// double angle = Math.PI * 2 * i / electron;
			// System.out.println("before pointX-->"+pointX+" before pointY--->"+pointY+" Angle-->"+angle);
			// System.out.println("before x-->"+x+" before y--->"+y);
			pointX = pointX - 5 + (r / 2 * Math.cos(Math.toRadians(angle)));
			pointY = pointY + (r / 2 * Math.sin(Math.toRadians(angle)));
			System.out.println("After pointX-->" + pointX + " pointY--->"
					+ pointY + " Angle-->" + angle);
			Shape circle3 = new Ellipse2D.Double(pointX, pointY, 10, 10);
			g.fill(circle3);
			pointX = pointX1;
			pointY = pointY1;
			angle += tempAngle;

		}
		g.drawString(text, 93, 530);
	}

	public static void main(String s[]) {
		ElectronConfiguration ec=new ElectronConfiguration(); 
		LinkedHashMap<String, Element> hm = PeriodicTable.getPeriodicTableAtomNameMap();
		LinkedHashMap<Integer, Element> hm2 = PeriodicTable.getPeriodicTableAtomNumberMap();
		Element ele = hm.get("");
		Element ele2=hm2.get(10);
		System.out.println(ele2.getMoleName()+" " +ele2.getMoleSymbol());
		
		int atomicNumber = 0;
		String atomicName= null;
		if(null != ele){
		atomicNumber = ele.getPeriodicId();
		atomicName=ele.getMoleName();
	    }else if(null != ele2){
	    	atomicNumber = ele2.getPeriodicId();
			atomicName=ele2.getMoleName();
	    }
		String electronConfiguration = ec.getElectronConfiguration(atomicNumber,atomicName);
		System.out.println(electronConfiguration);

	}
}