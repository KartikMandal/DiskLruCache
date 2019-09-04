package com.kartik.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesLoad {
	private final Properties configProp = new Properties();
	private PropertiesLoad()
	   {
	      //Private constructor to restrict new instances
	      InputStream in = this.getClass().getClassLoader().getResourceAsStream("ApplicationResources.properties");
	      System.out.println("Read all properties from file");
	      try {
	          configProp.load(in);
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	   }
	 
	   //Bill Pugh Solution for singleton pattern
	   private static class LazyHolder
	   {
	      private static final PropertiesLoad INSTANCE = new PropertiesLoad();
	   }
	 
	   public static PropertiesLoad getInstance()
	   {
	      return LazyHolder.INSTANCE;
	   }
	    
	   public String getProperty(String key){
	      return configProp.getProperty(key);
	   }
	    
	   public Set<String> getAllPropertyNames(){
	      return configProp.stringPropertyNames();
	   }
	    
	   public boolean containsKey(String key){
	      return configProp.containsKey(key);
	   }
	
	
	
	/*private static String CONFIG_TOOL_INIT_FILE = "ApplicationResources.properties";		 
	private static Properties properties = new Properties();
	
	*//**
	 *  Load ConfigTool.properties file
	 *//*
	public static void loadPropertyFile(){
		try {            			
			System.out.println(" Loading ConfigTool.properties file ...");		
			//InputStream in = getClass().getClassLoader().getResourceAsStream(CONFIG_TOOL_INIT_FILE);
			InputStream in = PropertiesLoad.class.getClass().getResourceAsStream(CONFIG_TOOL_INIT_FILE);
		 	properties.load(in);
			System.out.println(properties.getProperty("TIME_OUT"));
			System.out.println(" Completed Loading of ConfigTool.properties file ...");
		}catch (Exception exception) {
			System.out.println("Exception occured while loading ConfigTool.properties file.");
			//exception.printStackTrace();
		}
 
	}
	
	*//**
	 * @param key
	 * @return
	 *//*
	public String getPropertyValue(String key){

		String value = null;
		try {
			loadPropertyFile();
			value = properties.getProperty(key);			 
		} catch (Exception e) {
			System.out.println("Exception occured while reading value for the key:"+key);
		}
		return value;
	}*/

	public static void main(String[] args) {
		System.out.println(PropertiesLoad.getInstance().getProperty("TIME_OUT"));
		/*String sessionTime = prop.getPropertyValue("TIME_OUT");
		System.out.println("Kartik ------------>>>>>" + sessionTime);*/

	}

}
