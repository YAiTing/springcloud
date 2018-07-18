package com.yb.user.service;

import com.yb.user.entity.Person;
import com.yb.user.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yangb
 * @Description:
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public Long save(Person entity) {
		return personRepository.save(entity);
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}
}
