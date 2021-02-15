package com.ebi.assignment.task.ebiassignmenttask.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ebi.assignment.task.ebiassignmenttask.model.Person;
import com.ebi.assignment.task.ebiassignmenttask.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
public class PersonServiceTest {

	@Autowired
	PersonService personService;
	//@InjectMocks
	//PersonServiceImpl personService;
	
	@MockBean
	private PersonRepository personRepository;
	
	@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void savePerson() {
		
		Person person = new Person();
		person.setAge("34");
		person.setFavourite_colour("Pink");
		person.setFirst_name("Little");
		person.setLast_name("Angle");
		
		Mockito.when(personRepository.save(person)).thenReturn(person);
		Assert.assertEquals(personService.save(person), person);
	}
	
	@Test
	public void saveAllPerson() {
		
		Person person = new Person();
		person.setAge("34");
		person.setFavourite_colour("Pink");
		person.setFirst_name("Little");
		person.setLast_name("Angle");
		
		Person person1 = new Person();
		person1.setAge("24");
		person1.setFavourite_colour("Red");
		person1.setFirst_name("Big");
		person1.setLast_name("Angle");
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		persons.add(person1);
		
		Mockito.when(personRepository.saveAll(persons)).thenReturn(persons);
		Assert.assertEquals(personService.save(persons).size(), persons.size());
	}
	
	@Test
	public void findPersonByFirst_name() {
		
		Person person = new Person();
		person.setAge("34");
		person.setFavourite_colour("Pink");
		person.setFirst_name("Little");
		person.setLast_name("Angle");
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		
		Mockito.when(personRepository.findByfirst_name("Little")).thenReturn(persons);
		Assert.assertEquals(personService.findByfirstName("Little").size(), persons.size());
			        
	}
	
	@Test
	public void findPersonByLast_name() {
		
		Person person = new Person();
		person.setAge("34");
		person.setFavourite_colour("Pink");
		person.setFirst_name("Little");
		person.setLast_name("Angle");
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(person);
		
		Mockito.when(personRepository.findBylast_name("Angle")).thenReturn(persons);
		Assert.assertEquals(personService.findByLastName("Angle").get(0), persons.get(0));
			        
	}
}
