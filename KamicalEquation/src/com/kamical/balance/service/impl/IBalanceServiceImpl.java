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
package com.kamical.balance.service.impl;

import java.util.LinkedHashMap;

import com.kamical.balance.model.Element;
import com.kamical.balance.model.PeriodicTable;
import com.kamical.balance.service.IBalanceService;
import com.kamical.balance.util.EquationBalancer;
import com.kamical.balance.util.Weight;
import com.kamical.desgin.model.ElectronConfiguration;

public class IBalanceServiceImpl implements IBalanceService {

	@Override
	public String balanceChemicalEquation(String equation) {
		EquationBalancer balance=new EquationBalancer();
		return balance.equationBalance(equation);
	}

	@Override
	public String getMoleWeight(String element) {
		Weight w=new Weight();
		return w.getMoleWeight(element);
	}

	@Override
	public void displayOrbitImageByPeroidId(int peroidId) {
		ElectronConfiguration ec=new ElectronConfiguration();
		LinkedHashMap<Integer, Element> hm2 = PeriodicTable.getPeriodicTableAtomNumberMap();
		Element ele2=hm2.get(peroidId);
		System.out.println(ele2.getMoleName()+" " +ele2.getMoleSymbol());
		int atomicNumber = 0;
		String atomicName= null;
		 if(null != ele2){
	    	atomicNumber = ele2.getPeriodicId();
			atomicName=ele2.getMoleName();
	    }
		String electronConfiguration = ec.getElectronConfiguration(atomicNumber,atomicName);
		System.out.println(electronConfiguration);
		
	}

	@Override
	public void displayOrbitImageByAtomSymbol(String atomSymbol) {
		ElectronConfiguration ec=new ElectronConfiguration();
		LinkedHashMap<String, Element> hm = PeriodicTable.getPeriodicTableAtomNameMap();
		Element ele = hm.get(atomSymbol);
		
		int atomicNumber = 0;
		String atomicName= null;
		if(null != ele){
		atomicNumber = ele.getPeriodicId();
		atomicName=ele.getMoleName();
	    }
		String electronConfiguration = ec.getElectronConfiguration(atomicNumber,atomicName);
		System.out.println(electronConfiguration);
		
	}

}
