package ru.alishev.springcourse.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Neil Alishev
 */
public class Person {
    private int person_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String full_name;

    @Min(value = 0, message = "Age should be greater than 0")
    private int birth_year;

    public Person() {

    }

    public Person(int id, String name, int age) {
        this.person_id = id;
        this.full_name = name;
        this.birth_year = age;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int id) {
        this.person_id = id;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String name) {
        this.full_name = name;
    }

	public int getBirthYear() {
		return birth_year;
	}

	public void setBirthYear(int birthYear) {
		this.birth_year = birthYear;
	}
}
