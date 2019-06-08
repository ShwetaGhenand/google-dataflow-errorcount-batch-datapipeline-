package com.example.config;

import java.util.Properties;

import com.example.common.Constants;
import com.example.util.CommonUtil;

public class EnvironmentConfig {

	private static Properties properties = null;
	public static String INPUT_FILE_PATH;
	public static String OUTPUT_FILE_PATH;

	static {
		properties = CommonUtil.getConfigPropertiesFn();
		loadEnvironmentVariable();
	}

	public static void loadEnvironmentVariable() {
		INPUT_FILE_PATH = properties.getProperty(Constants.INPUT_FILE_PATH);
		OUTPUT_FILE_PATH = properties.getProperty(Constants.OUTPUT_FILE_PATH);
	}
}
