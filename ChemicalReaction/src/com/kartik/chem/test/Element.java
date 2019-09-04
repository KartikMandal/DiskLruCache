package com.kartik.chem.test;

public class Element {
	private int periodicId;
	private String moleSymbol;
	private String moleName;
	private double weight;
	private String[] valency;
	private String defaultValancy;
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
	public String getDefaultValancy() {
		return defaultValancy;
	}
	/**
	 * @param defaultValancy the defaultValancy to set
	 */
	public void setDefaultValancy(String defaultValancy) {
		this.defaultValancy = defaultValancy;
	}
	

}
