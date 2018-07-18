package com.yb.springcloud.springcloudsleuth.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @Author: yangb
 * @Date: Created in 11:02 2017/12/8
 */
@RestController
public class SleuthDemonController {

	private final Logger logger = LoggerFactory.getLogger(SleuthDemonController.class);

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("")
	public String index() {
		String returnValue = "Hello World";
		//INFO [spring-cloud-sleuth,b45457766d184ad1,b45457766d184ad1,false]
		logger.info("{} index(): {}", getClass().getSimpleName(), returnValue);
		return returnValue;
	}

	/**
	 * sleuth -> zuul -> person-client -> person-service
	 * @return
	 */
	@GetMapping("/to/zuul/person-client/person/list")
	public Object zuul() {
		logger.info("spring-cloud-sleuth#zuul()");
		String url = "http://spring-cloud-zuul//person-client/person/list";
		return restTemplate.getForObject(url, Object.class);
	}
}
