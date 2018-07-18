package com.yb.user.service;

import com.yb.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: yangb
 * @Description:
 */
@Service
public class UserServiceProxy implements UserService {

	private static final String PROVIDER_SERVICE_URL_PREFIX = "http://user-service-provider:9090";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public boolean save(User entity) {
		User returnValue = restTemplate.postForObject(PROVIDER_SERVICE_URL_PREFIX + "/user/save", entity, User.class);
		return returnValue != null;
	}

	@Override
	public List<User> findAll() {
		return restTemplate.getForObject(PROVIDER_SERVICE_URL_PREFIX + "/user/list", List.class);
	}
}
