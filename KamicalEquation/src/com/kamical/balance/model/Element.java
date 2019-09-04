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
package com.kamical.balance.model;
/**
 * 
 * @author Kartik Chandra Mandal
 *
 */
public class Element {
	private int periodicId;
	private String moleSymbol;
	private String moleName;
	private double weight;
	private String[] valency;
	private String defaultAtomValancy;
	private float electroNegativity;
	private String type;// Metal/Metaloids/Non-metal
	/**
	 * @return the periodicId
	 */
	public int getPeriodicId() {
		return periodicId;
	}
	/**
	 * @param periodicId the periodicId to set
	 */
	public void setPeriodicId(int periodicId) {
		this.periodicId = periodicId;
	}
	/**
	 * @return the moleSymbol
	 */
	public String getMoleSymbol() {
		return moleSymbol;
	}
	/**
	 * @param moleSymbol the moleSymbol to set
	 */
	public void setMoleSymbol(String moleSymbol) {
		this.moleSymbol = moleSymbol;
	}
	/**
	 * @return the moleName
	 */
	public String getMoleName() {
		return moleName;
	}
	/**
	 * @param moleName the moleName to set
	 */
	public void setMoleName(String moleName) {
		this.moleName = moleName;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	/**
	 * @return the valency
	 */
	public String[] getValency() {
		return valency;
	}
	/**
	 * @param valency the valency to set
	 */
	public void setValency(String[] valency) {
		this.valency = valency;
	}
	/**
	 * @return the defaultValancy
	 */
	public String getDefaultAtomValancy() {
		return defaultAtomValancy;
	}
	/**
	 * @param defaultAtomValancy the defaultValancy to set
	 */
	public void setDefaultAtomValancy(String defaultAtomValancy) {
		this.defaultAtomValancy = defaultAtomValancy;
	}
	/**
	 * @return the electroNegativity
	 */
	public float getElectroNegativity() {
		return electroNegativity;
	}
	/**
	 * @param electroNegativity the electroNegativity to set
	 */
	public void setElectroNegativity(float electroNegativity) {
		this.electroNegativity = electroNegativity;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	

}
