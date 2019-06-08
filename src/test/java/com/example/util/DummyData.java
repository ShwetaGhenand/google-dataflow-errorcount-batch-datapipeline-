package com.example.util;

import java.util.Arrays;
import java.util.List;

public class DummyData {
	public static final String[] LOG_ARRAY = new String[] {
			"2012-02-03 18:35:34 SampleClass6 [INFO] everything normal for id 577725851",
			"2012-02-03 18:35:34 SampleClass0 [ERROR] incorrect id  1886438513",
			"2012-02-03 18:35:34 SampleClass4 [FATAL] system problem at id 1991281254",
			"2012-02-03 18:35:34 SampleClass3 [WARN] missing id 423340895",
			"2012-02-03 18:55:54 SampleClass1 [ERROR] incorrect id  1159281532" };

	public static final List<String> Log_LINES = Arrays.asList(LOG_ARRAY);

	public static final String[] Error_ARRAY = new String[] {
			"2012-02-03 18:35:34 SampleClass0 [ERROR] incorrect id  1886438513",
			"2012-02-03 18:55:54 SampleClass1 [ERROR] incorrect id  1159281532" };

	public static long value = 6;
	
	   public static final String[] OUTPUT_ARRAY = new String[] {
		        "ERROR_COUNT::2"};
}
