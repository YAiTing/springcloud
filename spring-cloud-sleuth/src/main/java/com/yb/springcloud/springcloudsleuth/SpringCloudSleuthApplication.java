package com.yb.springcloud.springcloudsleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * https://github.com/openzipkin/docker-zipkin
 * start docker : sudo service docker start
 * zikpin server : docker run -d -p 9411:9411 openzipkin/zipkin
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudSleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSleuthApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
