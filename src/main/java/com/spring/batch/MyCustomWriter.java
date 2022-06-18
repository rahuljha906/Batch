package com.spring.batch;

import com.spring.batch.entity.Employee;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class MyCustomWriter extends FlatFileItemWriter<Employee> {

    public MyCustomWriter() {
        setResource(new FileSystemResource("data/employee.csv"));
        setLineAggregator(getDelimitedLineAggregator());
    }

    public DelimitedLineAggregator<Employee> getDelimitedLineAggregator() {
        BeanWrapperFieldExtractor<Employee> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Employee>();
        beanWrapperFieldExtractor.setNames(new String[] {"id", "name", "salary"});

        DelimitedLineAggregator<Employee> delimitedLineAggregator = new DelimitedLineAggregator<Employee>();
        delimitedLineAggregator.setDelimiter(",");
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        return delimitedLineAggregator;

    }
}
