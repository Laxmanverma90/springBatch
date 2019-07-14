create table person (
id int,
first_name varchar(10),
last_name varchar(10), 
email varchar(20), 
address varchar(20));

create table product(
id int,
category varchar(20),
name varchar(20),
brand varchar(20),
model varchar(20),
price decimal(10,2));

insert into product (id, category, name, brand, model, price) values(1, 'Lap top', 'HP Lap-top 1', 'HP', 'HP0MW1', 56700);
insert into product (id, category, name, brand, model, price) values(2, 'Lap top', 'HP Lap-top 2', 'HP', 'HP0MP4', 64000);

batch_job_execution
batch_job_execution_context
batch_job_execution_params
batch_job_execution_seq
batch_job_instance
batch_job_seq
batch_step_execution
batch_step_execution_context
batch_step_execution_seq


List of Reader and Writer in Spring batch :
https://docs.spring.io/spring-batch/3.0.x/reference/html/listOfReadersAndWriters.html

More Reader and Writer Example :-
https://examples.javacodegeeks.com/enterprise-java/spring/batch/spring-batch-itemreaders-and-itemwriters-example/
