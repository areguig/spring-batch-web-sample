package com.areguig.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by akli on 17/08/15.
 */
@RestController
public class BatchController {
	private static final Logger log = LoggerFactory
			.getLogger(BatchController.class);

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job job;

	@RequestMapping("/batch")
	public String runBatch() throws JobParametersInvalidException,
			JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException {

		jobLauncher.run(job, new JobParameters());
		return "done.";
	}
}
