package com.yb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: yangb
 * @Description:
 */
//@SpringCloudApplication
@SpringBootApplication
@EnableDiscoveryClient
public class UserConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserConsumerApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate buildRestTemplate() {
		return new RestTemplate();
	}
}
