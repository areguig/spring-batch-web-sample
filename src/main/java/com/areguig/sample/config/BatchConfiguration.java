package com.areguig.sample.config;

import com.areguig.sample.batch.SampleItemProcessor;
import com.areguig.sample.batch.SampleItemWriter;
import com.areguig.sample.batch.SampleJobExecutionListener;
import com.areguig.sample.bean.SampleItem;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * Created by akli on 17/08/15.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Bean
	public ItemReader<SampleItem> reader() {
		FlatFileItemReader<SampleItem> reader = new FlatFileItemReader<SampleItem>();

		reader.setResource(new ClassPathResource("sample-data.csv"));
		reader.setLineMapper(new DefaultLineMapper<SampleItem>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[]{"itemValue"});
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<SampleItem>() {{
				setTargetType(SampleItem.class);
			}});
		}});
		return reader;
	}

	@Bean
	public ItemProcessor<SampleItem, SampleItem> processor() {
		return new SampleItemProcessor();
	}

	@Bean
	public ItemWriter<SampleItem> writer(DataSource dataSource) {
		ItemWriter<SampleItem> writer = new SampleItemWriter();
		return writer;
	}

	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1,
			SampleJobExecutionListener listener) {
		return jobs.get("importUserJob").incrementer(new RunIdIncrementer())
				.listener(listener).flow(s1).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory,
			ItemReader<SampleItem> reader, ItemWriter<SampleItem> writer,
			ItemProcessor<SampleItem, SampleItem> processor) {
		return stepBuilderFactory.get("step1").<SampleItem, SampleItem>chunk(20)
				.reader(reader).processor(processor).writer(writer).build();
	}
}
