package com.yb;

import com.yb.user.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * @Author: yangb
 * @Description:
 */
//@SpringCloudApplication
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@Import(WebConfig.class)
@EnableOAuth2Client
@EnableOAuth2Sso
public class PersonServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonServiceProviderApplication.class, args);
	}
}
