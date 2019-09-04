/*package com.kamical.color.service.impl;

import com.kamical.color.service.IElement;

*//**
 * A read-only class used by {@link Elements} for the natural elements. This class is not to
 * be used than by only {@link Elements}.
 *
 * @author      egonw
 * @cdk.module  core
 * @cdk.githash
 *//*
final class NaturalElement implements IElement{

    private final String  element;
    private final Integer atomicNumber;

    public NaturalElement(String element, Integer atomicNumber) {
        this.element = element;
        this.atomicNumber = atomicNumber;
    }

    

    *//**
	 * @return the element
	 *//*
	public String getElement() {
		return element;
	}



	*//**
	 * @return the atomicNumber
	 *//*
	public Integer getAtomicNumber() {
		return atomicNumber;
	}



	@Override
    public Object clone() {
        return this;
    }



	@Override
	public void setAtomicNumber(Integer atomicNumber) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return element;
	}



	@Override
	public void setSymbol(String symbol) {
		// TODO Auto-generated method stub
		
	}
}*/