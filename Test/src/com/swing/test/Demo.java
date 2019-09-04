package com.swing.test;
//http://butane.chem.uiuc.edu/cyerkes/Chem102AEFa07/Lecture_Notes_102/newLecture%2014-102.htm
//https://stackoverflow.com/questions/9871727/how-to-get-coordinates-of-a-point-in-a-coordinate-system-based-on-angle-and-dist
//https://math.stackexchange.com/questions/143932/calculate-point-given-x-y-angle-and-distance
//https://stackoverflow.com/questions/17764525/converting-result-of-math-sinx-into-a-result-for-degrees-in-java
public class Demo {
	public static void main(String[] args){
		double pointX,pointY,pointZ,x = 0,y = 0,z=0,distance = 10,angle = 30;
		
		
		pointX = Math.cos(Math.toRadians( angle ));
		pointY = Math.sin(Math.toRadians( angle ));
		pointZ = Math.sin(Math.toRadians( angle ));
		System.out.println("pointX  -->"+pointX+" pointY-->"+pointY);
		
		pointX = x + distance * Math.cos(Math.toRadians( angle ));
		pointY = y + distance * Math.sin(Math.toRadians( angle ));
		
		System.out.println("pointX  -->"+pointX+" pointY-->"+pointY);
		
		angle = 45;
		pointX = x + distance * Math.cos(Math.toRadians( angle ));
		pointY = y + distance * Math.sin(Math.toRadians( angle ));
		
		System.out.println("pointX  -->"+pointX+" pointY-->"+pointY);
		angle = 60;
		pointX = x + distance * Math.cos(Math.toRadians( angle ));
		pointY = y + distance * Math.sin(Math.toRadians( angle ));
		
		System.out.println("pointX  -->"+pointX+" pointY-->"+pointY);
		
		angle = 90;
		pointX = x + distance * Math.cos(Math.toRadians( angle ));
		pointY = y + distance * Math.sin(Math.toRadians( angle ));
		
		System.out.println("pointX  -->"+pointX+" pointY-->"+pointY);
		
		angle = 120;
		pointX = x + distance * Math.cos(Math.toRadians( angle ));
		pointY = y + distance * Math.sin(Math.toRadians( angle ));
		
		System.out.println("pointX  -->"+pointX+" pointY-->"+pointY);
		angle = 180;
		pointX = x + distance * Math.cos(Math.toRadians( angle ));
		pointY = y + distance * Math.sin(Math.toRadians( angle ));
		
		System.out.println("pointX  -->"+pointX+" pointY-->"+pointY);
		angle = 210;
		pointX = x + distance * Math.cos(Math.toRadians( angle ));
		pointY = y + distance * Math.sin(Math.toRadians( angle ));
		
		System.out.println("pointX  -->"+pointX+" pointY-->"+pointY);
		angle = 290;
		pointX = x + distance * Math.cos(Math.toRadians( angle ));
		pointY = y + distance * Math.sin(Math.toRadians( angle ));
		
		System.out.println("pointX  -->"+pointX+" pointY-->"+pointY);
	}

}
