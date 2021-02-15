package com.ebi.assignment.task.ebiassignmenttask.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebi.assignment.task.ebiassignmenttask.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

	
	@Query("Select s from Person s where s.first_name=:first_name")
	List<Person> findByfirst_name(String first_name);

	@Query("Select s from Person s where s.last_name=:last_name")
	List<Person> findBylast_name(String last_name);
	
	@Query("Select s from Person s where s.first_name=:first_name and s.last_name=:last_name")
	List<Person> findByfirst_nameAndlast_name(String first_name, String last_name);
	
	@Query("Select s from Person s where s.first_name=:first_name and s.last_name=:last_name and s.age=:age and s.favourite_colour=:favourite_colour")
	List<Person> findByAllParams(String first_name, String last_name, String age, String favourite_colour);

	@Query("Select s from Person s where s.age=:age")
	List<Person> findByAge(String age);
	
	@Query("Select s from Person s where s.favourite_colour=:favourite_colour")
	List<Person> findByfavourite_colour(String favourite_colour);

	
}
