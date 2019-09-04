package com.code.thoughtwork;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DayOfDate {
Map<String,Integer> map=new HashMap<String,Integer>();

Map<Integer,Integer> mapNum=new HashMap<Integer,Integer>();
	
public void putCode(){
	map.put("JAN",0);
	map.put("FEB",3);
	map.put("MAR",3);
	map.put("APR",6);
	map.put("MAY",1);
	map.put("JUN",4);
	map.put("JUL",6);
	map.put("AUG",2);
	map.put("SEP",5);
	map.put("OCT",0);
	map.put("NOV",3);
	map.put("DEC",5);
	
}

public void putCodeInNumber(){
	mapNum.put(1,0);
	mapNum.put(2,3);
	mapNum.put(3,3);
	mapNum.put(4,6);
	mapNum.put(5,1);
	mapNum.put(6,4);
	mapNum.put(7,6);
	mapNum.put(8,2);
	mapNum.put(9,5);
	mapNum.put(10,0);
	mapNum.put(11,3);
	mapNum.put(12,5);
	
}

	
	
	public static void main(String[] args) {
		String sDate1="31/12/1998";  
	    Date date1 = null;
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  Calendar calendar = Calendar.getInstance();
	      calendar.setTime(date1);
	    System.out.println(sDate1+"\t"+date1);  
	    int day=calendar.get(Calendar.DAY_OF_WEEK);
	   System.out.println(day);
	    

	}

}
