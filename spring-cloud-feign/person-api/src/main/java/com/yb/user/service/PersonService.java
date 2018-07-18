package com.yb.user.service;

import com.yb.user.entity.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Author: yangb
 * @Description:
 */
@FeignClient(value = "person-service")
public interface PersonService {

	@PostMapping("/person/save")
	Long save(Person entity);

	@GetMapping("/person/list")
	List<Person> findAll();
}
