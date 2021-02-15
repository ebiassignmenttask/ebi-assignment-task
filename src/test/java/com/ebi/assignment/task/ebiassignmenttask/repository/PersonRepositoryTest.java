package com.ebi.assignment.task.ebiassignmenttask.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.ebi.assignment.task.ebiassignmenttask.model.Person;
/*
 * This spring jpa implementation is based on 
 * 
 * */
@RunWith(SpringRunner.class)
@DataJpaTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class PersonRepositoryTest {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	PersonRepository personRepo;
	
	@Test
	public void findByFirstNamePersonDetails() {
		
		
		Person person = new Person();
		person.setAge("34");
		person.setFavourite_colour("Pink");
		person.setFirst_name("Little");
		person.setLast_name("Angle");
				
		Person personSaved = em.persist(person);
	
		List<Person> findByfirst_name = personRepo.findByfirst_name("Little");
		
		Assert.assertEquals(personSaved.getFirst_name(), findByfirst_name.get(0).getFirst_name());
			
	}
	
	
	
}
