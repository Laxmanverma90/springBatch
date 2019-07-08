package com.hcl.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.hcl.bean.PersonBean;
import com.hcl.dto.Product;
import com.hcl.listener.JobCompletionNotificationListener;
import com.hcl.mapper.ProductRowMapper;
import com.hcl.processor.PersonItemProcessor;
import com.hcl.processor.ProductItemProcessor;

//provides many utilitty classes which reduces custome code 

// adds critical many beans for batch processing
@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

/*	@Bean
	public FlatFileItemReader<PersonBean> reader() {
		FlatFileItemReader<PersonBean> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("data.csv"));
		reader.setLineMapper(new DefaultLineMapper<PersonBean>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setNames(new String[] { "firstName", "lastName", "email", "address" });
			}});
			setFieldSetMapper(new BeanWrapperFieldSetMapper<PersonBean>() {{
				setTargetType(PersonBean.class);
			}});
		}});
		return reader;
	}

	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	@Bean
    public JdbcBatchItemWriter<PersonBean> writer() {
        JdbcBatchItemWriter<PersonBean> writer = new JdbcBatchItemWriter<PersonBean>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<PersonBean>());
        writer.setSql("Insert into person (first_name, last_name, email, address) values (:firstName, :lastName, :email, :address)");
        writer.setDataSource(dataSource);
        return writer;
    }
	
	@Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step112())
                .end()
                .build();
    }
	
	@Bean
    public Step step112() {
        return stepBuilderFactory.get("step1")
                .<PersonBean, PersonBean> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }*/
	
	
	@Bean
	public JdbcCursorItemReader<Product> readerDb(){
		JdbcCursorItemReader<Product> cursorItemReader = new JdbcCursorItemReader<>();
		cursorItemReader.setDataSource(dataSource);
		cursorItemReader.setSql("SELECT id, category, name, brand, model, price FROM product");
		cursorItemReader.setRowMapper(new ProductRowMapper());
		return cursorItemReader;
	}
	
	@Bean
	public ProductItemProcessor processorDbCsv() {
		return new ProductItemProcessor();
	}
	
	@Bean
	public FlatFileItemWriter<Product> writerCsv(){
		
		FlatFileItemWriter<Product> writer = new FlatFileItemWriter<>();
		writer.setResource(new ClassPathResource("dataProduct.csv"));
		
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
				.reader(readerDb())
				.processor(processorDbCsv())
				.writer(writerCsv())
				.build();
	}
	
	@Bean
	public Job exportProductJob() {
		return jobBuilderFactory.get("exportJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}
	
}