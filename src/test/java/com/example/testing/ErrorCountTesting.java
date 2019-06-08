package com.example.testing;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.beam.sdk.transforms.DoFnTester;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.example.common.Constants;
import com.example.subprocess.ExtractErrorFn;
import com.example.subprocess.FormatAsTextFn;
import com.example.util.DummyData;

@SuppressWarnings("deprecation")
public class ErrorCountTesting {

	@Test
	public void extractErrorFnTesting() throws Exception {
		ExtractErrorFn extractErrorObj = new ExtractErrorFn();

		DoFnTester<String, String> fnTester = DoFnTester.of(extractErrorObj);
		List<String> testOutputs = fnTester.processBundle(DummyData.Log_LINES);

		Assert.assertThat(testOutputs, Matchers.hasItems(DummyData.Error_ARRAY));
	}

	@Test
	public void formatAsTextFnTesting() throws Exception {
		FormatAsTextFn formatAsTextFnObj = new FormatAsTextFn();

		String result = formatAsTextFnObj.apply(DummyData.value);

		assertEquals(result, Constants.ERROR_COUNT + DummyData.value);

	}

}
