package com.spring.batch.config;

import com.spring.batch.MyCustomReader;
import com.spring.batch.MyCustomWriter;
import com.spring.batch.MySCustomReader;
import com.spring.batch.MySCustomWriter;
import com.spring.batch.entity.Employee;
import com.spring.batch.entity.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    MyCustomReader myCustomReader;

    @Autowired
    MyCustomWriter myCustomWriter;

    @Autowired
    MySCustomReader mySCustomReader;

    @Autowired
    MySCustomWriter mySCustomWriter;

    @Bean
    public Job createJob() {
        return jobBuilderFactory.get("MyJob")
                .incrementer(new RunIdIncrementer())
                .flow(createStep())
                .next(createStep1()).end().build();
    }

   /* @Bean
    public Job createJob1() {
        return jobBuilderFactory.get("MyJob1")
                .incrementer(new RunIdIncrementer())
                .flow(createStep()).end().build();
    }*/

    @Bean
    public Step createStep() {
        return stepBuilderFactory.get("MyStep")
                .<Employee, Employee> chunk(1)
                .reader(myCustomReader)
                .writer(myCustomWriter)
                .build();
    }

    @Bean
    public Step createStep1() {
        return stepBuilderFactory.get("MyStep1")
                .<Student, Student> chunk(1)
                .reader(mySCustomReader)
                .writer(mySCustomWriter)
                .build();
    }
}
