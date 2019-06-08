package com.example.subprocess;

import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.common.Constants;

public class ExtractErrorFn extends DoFn<String, String> {

	private static final Logger LOG = LoggerFactory.getLogger(ExtractErrorFn.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ProcessElement
	public void processElement(ProcessContext c) {
		LOG.debug("extract error transform is started");
		try {
			if (c.element().contains(Constants.ERROR_NAME)) {
				c.output(c.element());
			}
		} catch (Exception e) {
			LOG.error("Failed ExtractError Transform:" + e.getLocalizedMessage(), e);
		}
		LOG.debug("extract error transform is finished");

	}
}