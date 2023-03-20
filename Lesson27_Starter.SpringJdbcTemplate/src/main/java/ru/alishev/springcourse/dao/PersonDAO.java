package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}


    public List<Person> index() {
    	return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    
    public Person show(String email) {
    	return jdbcTemplate.query("SELECT * FROM Person WHERE email=?", new Object[] {email},
    			new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, age, email, address) VALUES(?, ?, ?, ?)",
        		person.getName(), person.getAge(), person.getEmail(), person.getAddress());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=?, address=? WHERE id=?",
        		updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);

    }
    
    ///////////////////////////////////
    ///// Тестируем производительность пакетной вставки
    ///////////////////////////////////
    
    public void testMultipleUpdate() {
    	List<Person> people = create1000people();
    	
    	long before = System.currentTimeMillis();
    	
    	for (Person person : people) {
    		jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?)", person.getId(), person.getName(), person.getAge(), person.getEmail());
    	}
    	
    	long after = System.currentTimeMillis();
    	System.out.println("Time: " + (after-before));
    }
    
    public void testBatchUpdate() {
    	List<Person> list = create1000people();
    	
    	long before = System.currentTimeMillis();
    	
    	jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?, ?, ?, ?)", 
    			new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1, list.get(i).getId());
						ps.setString(2, list.get(i).getName());
						ps.setInt(3, list.get(i).getAge());
						ps.setString(4, list.get(i).getEmail());
					}
					
					@Override
					public int getBatchSize() {
						// TODO Auto-generated method stub
						return list.size();
					}
				});
    	
    	long after = System.currentTimeMillis();
    	System.out.println("Time: " + (after-before));
    }

	private List<Person> create1000people() {
		List<Person> list = new ArrayList<>();
		
		for (int i=0; i<1000; i++) {
			list.add(new Person(i, "Name" + i, 30, "name" + i + "@gmail.com", "address"));
		}
		
		return list;
	}
}
