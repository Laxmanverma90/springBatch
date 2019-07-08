package com.hcl.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.hcl.bean.PersonBean;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			
			List<PersonBean> result = jdbcTemplate.query("SELECT first_name, last_name, email, address FROM PERSON", new RowMapper<PersonBean>() {
				@Override
				public PersonBean mapRow(ResultSet rs, int row) throws SQLException {
					return new PersonBean(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));
				}
			});

			for (PersonBean personBean : result) {
				System.out.println("Found <" + personBean + "> in the database.");
			}
		}
	}
}
