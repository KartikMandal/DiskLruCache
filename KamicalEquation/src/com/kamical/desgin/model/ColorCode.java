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

public class ColorCode {
/**
 * 
 * @param result
 * @return
 */
	public static String converIntegerToHex(final int result) {
		  return "#"+Integer.toHexString(result);
		}
	/**
	 * 
	 * @param colorCode
	 * @return
	 */
	public static int converHexToInteger(final String colorCode) {
		  return Integer.parseInt(removeHashAndGetSubColorCode(colorCode), 16);
		}
	/**
	 * 
	 * @param colorCode
	 * @return
	 */
	public static String removeHashAndGetSubColorCode(final String colorCode) {
		  return  colorCode.substring(1, colorCode.length());
		}
	/**
	 * 
	 * @param colorOne
	 * @param colorTwo
	 * @return
	 */
	public static String getHexCode(final String colorOne, final String colorTwo){
		int first =(null!=colorOne)? converHexToInteger(colorOne):0;
		int second =(null!=colorTwo)? converHexToInteger(colorTwo):0;
		int result = first & second;//mixed color code so it is and operation
		return converIntegerToHex(result);//convert to hex code
	}
	public static void main(String[] args) {
		String s1="#FFFF00";//yellow color
		String s2="#EEE3FF"; //this is color less 
		String s3="#C0C0C0";//silver color
		String s4="#808080";//gray color
		/*final int a = converHexToInteger(s1); // 0b1111111111110111
		System.out.println(a);
		System.out.println("Hex value: "+Integer.toHexString(a));
		final int b = converHexToInteger(s2); // 0b0000000000000001
		System.out.println(b);
		System.out.println("Hex value: "+Integer.toHexString(b));
		final int c = converHexToInteger(s4); // 0b0000000000000001
		System.out.println(c);
		final int d = converHexToInteger(s3); // 0b0000000000000001
		System.out.println(d);
		final int result = a & b & d;
		System.out.println(result);
		String hex = converIntegerToHex(result);
        System.out.println("Hex value: "+hex);*/
        
        
		String s5=getHexCode(s1, s2);
		String s6=getHexCode(s5, s3);
		String s7=getHexCode(s6, s4);
		System.out.println("Hex value: "+s7);
	}
	
	
	

}
