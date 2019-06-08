package com.example.config;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface MyOptions extends PipelineOptions {
    @Description("Input file command line argument.")
    @Default.String("gs://gcp-bucket-test-01/input-data/sample.log")
    String getMyCustomOption();
    void setMyCustomOption(String myCustomOption);
  }
