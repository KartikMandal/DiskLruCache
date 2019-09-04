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

public class MoleElement {
	private String moleVariable;
	private String moleName;
	private int moleQuantity;
	private String moleQuantitySign;
	private String atomValency;
	/**
	 * 
	 */
	public MoleElement(){
		
	}
	/**
	 * 
	 * @param moleVariable
	 * @param moleName
	 * @param moleQuantity
	 */
	public MoleElement(String moleVariable,String moleName,int moleQuantity,String moleQuantitySign,String atomValency){
		this.moleName=moleName;
		this.moleQuantity=moleQuantity;
		this.moleVariable=moleVariable;
		this.moleQuantitySign=moleQuantitySign;
		this.atomValency=atomValency;
	}
	/**
	 * @return the moleVariable
	 */
	public String getMoleVariable() {
		return moleVariable;
	}
	/**
	 * @param moleVariable the moleVariable to set
	 */
	public void setMoleVariable(String moleVariable) {
		this.moleVariable = moleVariable;
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
	 * @return the moleQuantity
	 */
	public int getMoleQuantity() {
		return moleQuantity;
	}
	/**
	 * @param moleQuantity the moleQuantity to set
	 */
	public void setMoleQuantity(int moleQuantity) {
		this.moleQuantity = moleQuantity;
	}
	/**
	 * @return the moleQuantitySign
	 */
	public String getMoleQuantitySign() {
		return moleQuantitySign;
	}
	/**
	 * @param moleQuantitySign the moleQuantitySign to set
	 */
	public void setMoleQuantitySign(String moleQuantitySign) {
		this.moleQuantitySign = moleQuantitySign;
	}
	/**
	 * @return the atomValency
	 */
	public String getAtomValency() {
		return atomValency;
	}
	/**
	 * @param atomValency the atomValency to set
	 */
	public void setAtomValency(String atomValency) {
		this.atomValency = atomValency;
	}
	
	

}
