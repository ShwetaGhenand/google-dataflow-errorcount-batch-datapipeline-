/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.config.EnvironmentConfig;
import com.example.config.MyOptions;
import com.example.subprocess.ExtractErrorFn;
import com.example.subprocess.FormatAsTextFn;

/**
 * A starter example for writing Beam programs.
 *
 * <p>
 * The example takes two strings, converts them to their upper-case
 * representation and logs them.
 *
 * <p>
 * To run this starter example locally using DirectRunner, just execute it
 * without any additional parameters from your favorite development environment.
 *
 * <p>
 * To run this starter example using managed resource in Google Cloud Platform,
 * you should specify the following command-line options:
 * --project=<YOUR_PROJECT_ID>
 * --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE> --runner=DataflowRunner
 */
public class StarterPipeline {
	private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);

	public static void runErrorCount(MyOptions options) {

		LOG.debug("run errorcount pipeline function started");
		try {
			Pipeline pipeLine = Pipeline.create(options);

			PCollection<String> collections = pipeLine.apply("ReadLogFile",
					TextIO.read().from(options.getMyCustomOption()));

			collections.apply("FindError", ParDo.of(new ExtractErrorFn()))
					.apply("ErrorCount", Count.globally()).apply("FormatResult", MapElements.via(new FormatAsTextFn()))
					.apply("WriteCount", TextIO.write().to(EnvironmentConfig.OUTPUT_FILE_PATH));

			pipeLine.run();
		} catch (Exception e) {
			LOG.error("Failed pipeline:" + e.getLocalizedMessage(), e);
		}
		LOG.debug("run errorcount pipeline function finished");
	}

	public static void main(String[] args) {

		PipelineOptionsFactory.register(MyOptions.class);
		MyOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(MyOptions.class);
		runErrorCount(options);

	}
}
