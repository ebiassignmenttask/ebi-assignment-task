package com.ebi.assignment.task.ebiassignmenttask;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ebi.assignment.task.ebiassignmenttask.model.Person;
import com.ebi.assignment.task.ebiassignmenttask.model.PersonRoot;
import com.ebi.assignment.task.ebiassignmenttask.repository.PersonRepository;

@Component
public class DataLoader implements ApplicationRunner {

	
	private PersonRepository personRepo;
	
	@Autowired
	public DataLoader(PersonRepository personRepository) {
		this.personRepo = personRepository;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		PersonRoot personRoot = new PersonRoot();
		List<Person> persons = new ArrayList<Person>();
		Person person1 = new Person();
		person1.setAge("23");
		person1.setFavourite_colour("Gray");
		person1.setFirst_name("Muhammad");
		person1.setLast_name("Maroof");
		
		Person person2 = new Person();
		person2.setFirst_name("Muhammad");
		person2.setLast_name("Ibrahim");
		person2.setFavourite_colour("White");
		person2.setAge("1");
		
		persons.add(person1);
		persons.add(person2);
		
		personRoot.setPersonList(persons);
		
		personRepo.saveAll(personRoot.getPerson());

	}

}
