package com.yb.user.web.controller;

import com.yb.user.entity.Person;
import com.yb.user.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: yangb
 * @Description:
 */
@RestController
@RequestMapping("/person")
public class PersonClientController {

	private final PersonService personService;

	@Autowired
	public PersonClientController(PersonService personService) {
		this.personService = personService;
	}

	@PostMapping("/save")
	public Long save(@RequestBody String name) {
		Person entity = new Person();
		entity.setName(name);
		return personService.save(entity);
	}

	@GetMapping("/list")
	public List<Person> list() {
		return personService.findAll();
	}

}
