package com.kartik.chem.test;

public class MoleElement {
	private String moleVariable;
	private String moleName;
	private int moleQuantity;
	private String moleQuantitySign;
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
	public MoleElement(String moleVariable,String moleName,int moleQuantity,String moleQuantitySign){
		this.moleName=moleName;
		this.moleQuantity=moleQuantity;
		this.moleVariable=moleVariable;
		this.moleQuantitySign=moleQuantitySign;
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
	
	

}
