package com.yb;

import com.yb.user.service.PersonService;
import com.yb.user.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * @Author: yangb
 * @Description:
 */
//@SpringCloudApplication
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(clients = PersonService.class)
@Import(WebConfig.class)
@EnableOAuth2Sso
@EnableOAuth2Client
public class PersonClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonClientApplication.class, args);
	}

//	@Bean
//	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
//												 OAuth2ProtectedResourceDetails details) {
//		return new OAuth2RestTemplate(details, oauth2ClientContext);
//	}

	@Bean
	public OAuth2FeignRequestInterceptor requestInterceptor(
			OAuth2ClientContext oauth2ClientContext,
			ClientCredentialsResourceDetails oauth2RemoteResource){
		return new OAuth2FeignRequestInterceptor(oauth2ClientContext,oauth2RemoteResource);
	}
}
