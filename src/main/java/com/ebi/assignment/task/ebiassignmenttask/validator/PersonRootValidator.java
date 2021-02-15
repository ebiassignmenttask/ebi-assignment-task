package com.ebi.assignment.task.ebiassignmenttask.validator;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ebi.assignment.task.ebiassignmenttask.model.Person;

public class PersonRootValidator implements ConstraintValidator<PersonContraints, List<Person>>{

	private static final Logger logger = LoggerFactory.getLogger(PersonRootValidator.class);

	@Override
	public boolean isValid(List<Person> persons, ConstraintValidatorContext context){

		try {
			
			for(Person person:persons) {
				
				if(person.getFirst_name() == null || person.getFirst_name().equals("")) {
					return false;
				}
				
				if(person.getLast_name()== null || person.getLast_name().equals("")) {
					return false;
				}
				if(person.getAge() == null || person.getAge().equals("")) {
					return false;
				}
				if(person.getFavourite_colour() == null || person.getFavourite_colour().equals("")) {
					return false;
				}
			}
			
		} catch (Exception e) {
			logger.error("Error while trying to validate person details "+e.getMessage());
			return false;
		}
		return true;
	}

	
}
