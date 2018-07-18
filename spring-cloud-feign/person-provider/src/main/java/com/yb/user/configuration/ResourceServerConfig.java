package com.yb.user.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yangb
 * @Description:
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private ResourceServerProperties resource;

//	@Bean
//	public OAuth2RestTemplate restTemplate(UserInfoRestTemplateFactory factory) {
//		return factory.getUserInfoRestTemplate();
//	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resource.getId()).stateless(true);
	}


	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				// Since we want the protected resources to be accessible in the UI as well we need
				// session creation to be allowed (it's disabled by default in 2.0.6)
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//				.and()
				.requestMatchers().anyRequest()
				.and()
				.anonymous()
				.and()
				.authorizeRequests()
//                    .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
				.antMatchers("/person/**").authenticated();//配置order访问控制，必须认证过后才可以访问
	}

//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.requestMatcher(new OAuthRequestedMatcher())
//				.authorizeRequests()
//				.antMatchers(HttpMethod.OPTIONS).permitAll()
//				.anyRequest().authenticated();
//	}
//
//	private static class OAuthRequestedMatcher implements RequestMatcher {
//		@Override
//		public boolean matches(HttpServletRequest request) {
//			String auth = request.getHeader("Authorization");
//			boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
//			boolean haveAccessToken = request.getParameter("access_token")!=null;
//			return haveOauth2Token || haveAccessToken;
//		}
//	}
}
