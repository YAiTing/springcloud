package com.yb.springcloud.springcloudsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * https://cloud.spring.io/spring-cloud-static/spring-cloud-security/2.0.0.RELEASE/single/spring-cloud-security.html
 {[/oauth/authorize],methods=[POST]
 {[/oauth/token],methods=[POST]}
 {[/oauth/check_token]}
 {[/oauth/error]}
 password模式：http://localhost:8085/oauth/token?username=user_1&password=123456&grant_type=password&scope=app&client_id=client&client_secret=secret
 client模式：http://localhost:8085/oauth/token?grant_type=client_credentials&scope=app&client_id=client&client_secret=secret
 */
@SpringBootApplication
//@SessionAttributes("authorizationRequest")
@EnableAuthorizationServer
public class SpringCloudSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSecurityApplication.class, args);
	}

}
