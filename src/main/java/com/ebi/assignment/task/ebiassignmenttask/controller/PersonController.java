package com.ebi.assignment.task.ebiassignmenttask.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebi.assignment.task.ebiassignmenttask.model.Person;
import com.ebi.assignment.task.ebiassignmenttask.model.PersonRoot;
import com.ebi.assignment.task.ebiassignmenttask.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Operation(summary = "Get all Persons details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Persons details are Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "404", description = "Person not found", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
			@ApiResponse(responseCode = "401", description = "UnAuthorize", content = @Content) })
	@GetMapping("/persons")
	public ResponseEntity<?> getAllPersons() {
		List<Person> allPerson = personService.getAllPerson();
		if (allPerson == null || allPerson.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		PersonRoot personRoot = new PersonRoot();
		personRoot.setPersonList(allPerson);
		return ResponseEntity.status(HttpStatus.OK).body(personRoot);
	}

	@Operation(summary = "Get a Person by its id", hidden = true)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Person details are Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid person id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Person not found", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
			@ApiResponse(responseCode = "401", description = "UnAuthorize", content = @Content) })
	@GetMapping("/person/{id}")
	public ResponseEntity<?> getPerson(
			@Parameter(description = "id of person to be searched") @PathVariable("id") Long id) {
		Optional<Person> person = personService.findById(id);
		if (person.isPresent()) {
			List<Person> persons = new ArrayList<Person>();
			PersonRoot personRoot = new PersonRoot();
			persons.add(person.get());
			personRoot.setPersonList(persons);

			return ResponseEntity.status(HttpStatus.OK).body(person);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Operation(summary = "Get a Person by its first name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Person details are Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid person first name supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Person not found", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
			@ApiResponse(responseCode = "401", description = "UnAuthorize", content = @Content) })
	@GetMapping("/persons/first_name/{first_name}")
	public ResponseEntity<?> getPersonByFirst_name(
			@Parameter(description = "first_name of person to be searched") @PathVariable("first_name") String first_name) {
		List<Person> persons = personService.findByfirstName(first_name);
		if (!persons.isEmpty()) {
			PersonRoot personRoot = new PersonRoot();
			personRoot.setPersonList(persons);
			return ResponseEntity.status(HttpStatus.OK).body(personRoot);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Operation(summary = "Get a Person by its last name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Person details are Found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid person first name supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Person not found", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
			@ApiResponse(responseCode = "401", description = "UnAuthorize", content = @Content) })
	@GetMapping("/persons/last_name/{last_name}")
	public ResponseEntity<?> getPersonByLast_name(
			@Parameter(description = "last_name of person to be searched") @PathVariable("last_name") String last_name) {

		List<Person> persons = personService.findByLastName(last_name);
		if (!persons.isEmpty()) {
			PersonRoot personRoot = new PersonRoot();
			personRoot.setPersonList(persons);
			return ResponseEntity.status(HttpStatus.OK).body(personRoot);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Operation(summary = "Save persons record")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Persons saved successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid person details", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
			@ApiResponse(responseCode = "401", description = "UnAuthorize", content = @Content) })
	@PostMapping("/persons")
	public ResponseEntity<?> savePerson(@RequestBody PersonRoot persons) {

		List<Person> saveAll = personService.saveAll(persons.getPerson());
		PersonRoot personRoot = new PersonRoot();
		personRoot.setPersonList(saveAll);
		return ResponseEntity.status(HttpStatus.CREATED).body(personRoot);
	}

	@Operation(summary = "Update persons record")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Persons updated successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid person details", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
			@ApiResponse(responseCode = "401", description = "UnAuthorize", content = @Content) })
	@PutMapping("/persons/first_name/{first_name}")
	public ResponseEntity<?> updatePerson(@RequestBody PersonRoot persons,
			@PathVariable("first_name") String first_name) {

		List<Person> getAllPersonByfirstName = personService.findByfirstName(first_name);

		if (getAllPersonByfirstName.size() == 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (getAllPersonByfirstName.size() > 1) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		PersonRoot personRoot = new PersonRoot();
		for (Person person : getAllPersonByfirstName) {
			person.setFirst_name(persons.getPerson().get(0).getFirst_name());
			person.setLast_name(persons.getPerson().get(0).getLast_name());
			person.setAge(persons.getPerson().get(0).getAge());
			person.setFavourite_colour(persons.getPerson().get(0).getFavourite_colour());
			Person save = personService.save(person);

			List<Person> updatedPerson = new ArrayList<Person>();
			updatedPerson.add(save);
			personRoot.setPersonList(updatedPerson);
			return ResponseEntity.status(HttpStatus.OK).body(personRoot);
		}

		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	}

	@Operation(summary = "Update persons record by Id", hidden = true)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Persons updated successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid person details", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
			@ApiResponse(responseCode = "401", description = "UnAuthorize", content = @Content) })
	@PutMapping("/persons/{id}")
	public ResponseEntity<?> updatePersonById(@RequestBody PersonRoot persons, @PathVariable("id") Long id) {

		Optional<Person> findPersonById = personService.findById(id);
			
		if (findPersonById.isPresent()) {
			Person person = findPersonById.get();
			person.setFavourite_colour(persons.getPerson().get(0).getFavourite_colour());
			person.setFirst_name(persons.getPerson().get(0).getFirst_name());
			person.setAge(persons.getPerson().get(0).getAge());
			person.setLast_name(persons.getPerson().get(0).getLast_name());
			personService.save(person);
			return ResponseEntity.status(HttpStatus.OK).body(persons);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@Operation(summary = "Delete Person")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Person deleted successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid person supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Person not found", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
			@ApiResponse(responseCode = "401", description = "UnAuthorize", content = @Content) })
	@DeleteMapping("/persons")
	public ResponseEntity<?> deletePerson(@RequestBody PersonRoot personRoot) {

		for (Person person : personRoot.getPerson()) {
			List<Person> foundPerson = personService.findByfirstNameAndLastNameAndAgeAndColour(person.getFirst_name(),
					person.getLast_name(), person.getAge(), person.getFavourite_colour());
			System.out.println("Found Person size is : " + foundPerson.size());
			if (foundPerson.size() == 0 || foundPerson.size() > 1) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			} else {
				personService.deletePerson(foundPerson.get(0));
			}
		}

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Operation(summary = "Delete Person by its first name", hidden = true)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Person deleted successfully", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Person.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid person first name supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Person not found", content = @Content),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
			@ApiResponse(responseCode = "401", description = "UnAuthorize", content = @Content) })
	@DeleteMapping("/persons/{id}")
	public ResponseEntity<?> deletePersonById(
			@Parameter(description = "Id of person to be deleted") @PathVariable("id") Long id) {

		Optional<Person> findPersonById = personService.findById(id);

		if (findPersonById.isPresent()) {
			personService.deletePerson(findPersonById.get());
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
