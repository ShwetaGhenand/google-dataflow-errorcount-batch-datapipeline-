package com.example.config;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.ValueProvider;

public interface MyOptions extends PipelineOptions {
  @Description("Input file command line argument.")
  @Default.String("gs://gcp-bucket-test-01/input-data/sample.log")
  ValueProvider<String> getMyCustomOption();

  void setMyCustomOption(ValueProvider<String> myCustomOption);
}
