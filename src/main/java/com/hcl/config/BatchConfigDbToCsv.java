package com.hcl.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.hcl.dto.Product;
import com.hcl.mapper.ProductRowMapper;
import com.hcl.processor.ProductItemProcessor;

//provides many utilitty classes which reduces custome code 

// adds critical many beans for batch processing
//@Configuration
//@EnableBatchProcessing
public class BatchConfigDbToCsv {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	/*@Bean
	public JdbcCursorItemReader<Product> reader(){
		JdbcCursorItemReader<Product> cursorItemReader = new JdbcCursorItemReader<>();
		cursorItemReader.setDataSource(dataSource);
		cursorItemReader.setSql("SELECT id, category, name, brand, model, price FROM product");
		cursorItemReader.setRowMapper(new ProductRowMapper());
		return cursorItemReader;
	}
	
	@Bean
	public ProductItemProcessor processor() {
		return new ProductItemProcessor();
	}
	
	@Bean
	public FlatFileItemWriter<Product> writer(){
		
		FlatFileItemWriter<Product> writer = new FlatFileItemWriter<>();
		writer.setResource(new ClassPathResource("product.csv"));
		
		DelimitedLineAggregator<Product> lineAggregator = new DelimitedLineAggregator<>();
		lineAggregator.setDelimiter(",");
		
		BeanWrapperFieldExtractor<Product> fieldExtractor = new BeanWrapperFieldExtractor<>();
		fieldExtractor.setNames(new String[] {"id", "category", "name", "brand", "model", "price"});
		
		lineAggregator.setFieldExtractor(fieldExtractor);
		
		writer.setLineAggregator(lineAggregator);
		
		return writer;
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<Product, Product>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
	@Bean
	public Job exportProductJob() {
		return jobBuilderFactory.get("exportJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}*/
}