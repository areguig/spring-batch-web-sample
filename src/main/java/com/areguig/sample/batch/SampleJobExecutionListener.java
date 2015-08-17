package com.areguig.sample.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * Created by akli on 17/08/15.
 */
@Component
public class SampleJobExecutionListener implements JobExecutionListener {
	private static final Logger log = LoggerFactory
			.getLogger(SampleJobExecutionListener.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("Before Job!!");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("After Job!");
	}
}
