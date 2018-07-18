package com.yb.user.web.controller;

import com.yb.user.entity.User;
import com.yb.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: yangb
 * @Description: 服务提供方
 */
@RestController
@RequestMapping("/user")
public class UserProviderController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public User save(@RequestBody User user) {
		if (userService.save(user)) {
			return user;
		}
		return null;
	}

	@GetMapping("/list")
	public List<User> list() {
		return userService.findAll();
	}
}
