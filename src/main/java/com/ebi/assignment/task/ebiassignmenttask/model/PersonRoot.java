package com.ebi.assignment.task.ebiassignmenttask.model;

import java.util.ArrayList;
import java.util.List;

public class PersonRoot {

	private List<Person> person = new ArrayList<Person>();
	
	public PersonRoot() {	
		
	}

	public List<Person> getPerson() {
		return person;
	}

	public void setPersonList(List<Person> personList) {
		this.person = personList;
	}
	
}
