package com.yb.user.web.controller;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yb.user.entity.Person;
import com.yb.user.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.tracing.ProbeSkeleton;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: yangb
 * @Description: 服务提供方
 */
@RestController
@RequestMapping("/person")
public class UserProviderController {

	@Autowired
	private PersonService personService;

	@PostMapping("/save")
	public Long save(@RequestBody Person person) {
		return personService.save(person);
	}

	@GetMapping("/list")
//	@HystrixCommand(
//			fallbackMethod = "errorContent",
//			commandProperties = {
//					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
//							value = "100")
//			}
//	)
	public List<Person> list() throws InterruptedException {
		int value = ThreadLocalRandom.current().nextInt(200);
		System.out.println("helloWorld() costs" + value + " ms.");
		Thread.sleep(value);
		return personService.findAll();
	}

	public List<Person> errorContent() {
		return Lists.newArrayList(Collections.emptyList());
	}
}
