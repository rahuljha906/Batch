package com.spring.batch;

import com.spring.batch.entity.Student;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class MySCustomWriter extends FlatFileItemWriter<Student> {

    public MySCustomWriter() {
        setResource(new FileSystemResource("data/student.csv"));
        setLineAggregator(getDelimitedLineAggregator());
    }

    public DelimitedLineAggregator<Student> getDelimitedLineAggregator() {
        BeanWrapperFieldExtractor<Student> beanWrapperFieldExtractor = new BeanWrapperFieldExtractor<Student>();
        beanWrapperFieldExtractor.setNames(new String[] {"id", "name", "salary"});

        DelimitedLineAggregator<Student> delimitedLineAggregator = new DelimitedLineAggregator<Student>();
        delimitedLineAggregator.setDelimiter(",");
        delimitedLineAggregator.setFieldExtractor(beanWrapperFieldExtractor);
        return delimitedLineAggregator;

    }
}
