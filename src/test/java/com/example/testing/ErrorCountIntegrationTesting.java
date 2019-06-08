package com.example.testing;

import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.testing.TestPipeline;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.junit.Test;

import com.example.subprocess.ExtractErrorFn;
import com.example.subprocess.FormatAsTextFn;
import com.example.util.DummyData;

public class ErrorCountIntegrationTesting {

	@Test
	public void testErrorCount() {

		TestPipeline p = TestPipeline.create().enableAbandonedNodeEnforcement(false);

		PCollection<String> input = p.apply(Create.of(DummyData.Log_LINES));

		PCollection<String> error = input.apply(ParDo.of(new ExtractErrorFn()));
		PCollection<Long> count = error.apply(Count.globally());
		PCollection<String> output = count.apply(MapElements.via(new FormatAsTextFn()));

		PAssert.that(output).containsInAnyOrder(DummyData.OUTPUT_ARRAY);

		// Run the pipeline.
		p.run();

	}

}
