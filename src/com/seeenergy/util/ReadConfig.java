package com.seeenergy.util;

import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
	
	public static String getBykey(String key) {
		Properties props = new Properties();
		try {
			InputStream ins = ReadConfig.class.getClassLoader()
					.getResourceAsStream("jdbc.properties");
			props.load(ins);
			String val = props.getProperty(key);
			ins.close();
			return val;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	/**
	 * 获取配置文件信息，目录：config/config.properties
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		Properties props = new Properties();
		try {
			InputStream ins = ReadConfig.class.getClassLoader()
					.getResourceAsStream("config.properties");
			props.load(ins);
			String val = props.getProperty(key);
			ins.close();
			return val;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	/**
	 * 测试读取配置信息的方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(ReadConfig.getBykey("jdbc.driver"));
	}

}
