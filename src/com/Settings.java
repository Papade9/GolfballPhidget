package com;

import com.db.HQuery;
import com.db.tableclass.SettingsEntity;

import java.io.*;
import java.util.Properties;

public class Settings {
	private static Properties props;
	private static Properties _NREGproperties = new Properties();
	private static String SETTINGS_FILEPATH = null;
	private static String SETTINGS_NAME = null;

	private Settings() {}
	
	public static void init(String filepath, String name) {
		if(filepath == null || filepath.equals(""))
			filepath = "golfballConfig.xml";
		Settings.setFilepath(filepath);

		if(name.equals(""))
			name = "golfballConfig";
		SETTINGS_NAME = name;
		
		File propertiesFile = new File(SETTINGS_FILEPATH);
		InputStream inputStream = null;
		boolean loaded = false;
		
		props = new Properties();
		
		try {
			inputStream = new FileInputStream(propertiesFile);
			props.loadFromXML(inputStream);
			loaded = true;
		} catch(Exception e) {
			System.out.println("Unable to load properties. Creating new properties file.");
		}
		
		if(!loaded) {
			try {
				File f = new File(SETTINGS_FILEPATH);
		        OutputStream out = new FileOutputStream(f);
		        props.storeToXML(out, SETTINGS_NAME);
			} catch(Exception e) {
				System.out.println("Unable to save properties.");
			}
		}
	}
	
	public static String getSetting(String key, String defaultValue) {
		if(!props.containsKey(key))
			Settings.setSetting(key, defaultValue);
		
		return props.getProperty(key, defaultValue);
	}
	
	public static long getSetting(String key, Long defaultValue) {
		if(!props.containsKey(key))
			Settings.setSetting(key, Long.toString(defaultValue));
		
		long value = defaultValue;
		try {
			value = Long.parseLong(props.getProperty(key));
		} catch(Exception e) {}
		
		return value;
	}
	
	public static int getSetting(String key, Integer defaultValue) {
		if(!props.containsKey(key))
			Settings.setSetting(key, Integer.toString(defaultValue));
		
		int value = defaultValue;
		try {
			value = Integer.parseInt(props.getProperty(key));
		} catch(Exception e) {}
		
		return value;
	}
	
	public static boolean getSetting(String key, boolean defaultValue) {
		if(!props.containsKey(key))
			Settings.setSetting(key, Boolean.toString(defaultValue));
		
		boolean value = defaultValue;
		try {
			value = Boolean.parseBoolean(props.getProperty(key));
		} catch(Exception e) {}
		
		return value;
	}
	
	public static void setSetting(String key, String value) {
		props.setProperty(key, value);
		
		try {
			File f = new File(SETTINGS_FILEPATH);
	        OutputStream out = new FileOutputStream(f);
	        props.storeToXML(out, SETTINGS_NAME);
		} catch(Exception e) {
			System.out.println("Failed to save properties.");
		}
	}
	
	public static String getFilepath() {
		return SETTINGS_FILEPATH;
	}
	
	public static void setFilepath(String filepath) {
		SETTINGS_FILEPATH = filepath;
	}

	public static String getSetting(String setting){
		if(_NREGproperties.getProperty(setting) == null)
			loadSetting(setting);
		if(_NREGproperties.getProperty(setting) == null)
			return "";
		else
			return _NREGproperties.getProperty(setting);

	}
	private static void loadSetting(String setting){
		SettingsEntity settingRetrieved = new HQuery.selectRecord("from SettingsEntity where name=:setting","hibernate.cfg.xml",new HQuery.HQueryTuple("setting",setting)).query();
		if(settingRetrieved != null)
			_NREGproperties.setProperty(settingRetrieved.getName(),settingRetrieved.getValue());
	}
}
