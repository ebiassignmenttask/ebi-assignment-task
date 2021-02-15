package com.ebi.assignment.task.ebiassignmenttask.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebi.assignment.task.ebiassignmenttask.model.Person;
import com.ebi.assignment.task.ebiassignmenttask.repository.PersonRepository;
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepo;
	
	@Override
	public Person save(Person person) {
		
		Person personSaved = personRepo.save(person);
		return personSaved;
	}

	@Override
	public Optional<Person> findById(Long id) {
		
		return personRepo.findById(id);
	}

	@Override
	public List<Person> getAllPerson() {
		
		return personRepo.findAll();
	}

	@Override
	public boolean deletePerson(Person person) {
		
		 personRepo.delete(person);
		 return true;
	}

	@Override
	public List<Person> findByfirstName(String first_name) {
		return personRepo.findByfirst_name(first_name);
	}

	@Override
	public List<Person> saveAll(List<Person> persons) {
		List<Person> saveAll = personRepo.saveAll(persons);
		return saveAll;
	}

	@Override
	public List<Person> findByLastName(String last_name) {
		return personRepo.findBylast_name(last_name);
	}

	@Override
	public List<Person> findByAge(String age) {
		return personRepo.findByAge(age);
	}

	@Override
	public List<Person> findByfavouriteColour(String favourite_colour) {
		return personRepo.findByfavourite_colour(favourite_colour);
	}

	@Override
	public List<Person> findByfirstNameAndLastName(String first_name, String last_name) {
		return personRepo.findByfirst_nameAndlast_name(first_name, last_name);
	}

	@Override
	public List<Person> save(List<Person> persons) {
		List<Person> saveAll = personRepo.saveAll(persons);
		return saveAll;
	}

	@Override
	public List<Person> findByfirstNameAndLastNameAndAgeAndColour(String first_name, String last_name, String age,
			String favourite_colour) {
		
		return personRepo.findByAllParams(first_name, last_name, age, favourite_colour);
	}

}
