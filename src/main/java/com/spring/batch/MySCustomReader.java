package com.spring.batch;

import com.spring.batch.entity.Employee;
import com.spring.batch.entity.Student;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySCustomReader extends JdbcCursorItemReader<Student> implements ItemReader<Student>{

    public MySCustomReader(@Autowired DataSource dataSource) {
        setDataSource(dataSource);
        setSql("SELECT id, name, salary FROM student");
        setFetchSize(100);
        setRowMapper(new StudentRowMapper());
    }

    public class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student  = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setSalary(rs.getInt("salary"));
            return student;
        }
    }
}
