package com.ebi.assignment.task.ebiassignmenttask.service;

import java.util.List;
import java.util.Optional;

import com.ebi.assignment.task.ebiassignmenttask.model.Person;

public interface PersonService {

	public Person save(Person person);
	public List<Person> save(List<Person> presons);
	public Optional<Person> findById(Long id);
	public List<Person> getAllPerson();
	public boolean deletePerson(Person person);
	public List<Person> findByfirstName(String first_name);
	public List<Person> findByLastName(String last_name);
	public List<Person> findByAge(String age);
	public List<Person> findByfavouriteColour(String favourite_color);
	public List<Person> findByfirstNameAndLastName(String first_name, String lastName);
	public List<Person> findByfirstNameAndLastNameAndAgeAndColour(String first_name, String last_name, String Age, String favourite_colour);
	public List<Person> saveAll(List<Person> persons);
}
