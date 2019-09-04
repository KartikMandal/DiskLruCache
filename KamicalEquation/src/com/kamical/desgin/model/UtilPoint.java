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
/**
 * 
 * @author kmandal
 *
 */
public class UtilPoint {
	
	/**
	 * 
	 * @param n1
	 * @param n2
	 * @param distance
	 * @return
	 */
	public static CoordinatePoint getPoint(Node n1,Node n2, int distance){
		CoordinatePoint coordinatePoint =new CoordinatePoint();
		double pointX,pointY,pointZ,angle = 30;
		if(distance<=0){
			distance = 10;
		}
		if(angle<=0){
			angle = 30;
		}
		pointX = n1.xAxisPooint + distance * Math.cos(Math.toRadians( angle ));
		pointY = n1.yAxisPooint + distance * Math.sin(Math.toRadians( angle ));
		pointZ=0.0;
		coordinatePoint.setPointX(pointX);
		coordinatePoint.setPointY(pointY);
		coordinatePoint.setPointZ(pointZ);
		return coordinatePoint;
		
	}
	
	public static Node updatePoint(Node node2,CoordinatePoint cp){
		node2.xAxisPooint=(int) cp.getPointX();
	    node2.yAxisPooint=(int) cp.getPointY();
		return node2;
	}

}
