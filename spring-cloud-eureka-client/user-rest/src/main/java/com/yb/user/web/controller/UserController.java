package com.yb.user.web.controller;

import com.yb.user.entity.User;
import com.yb.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Single;

import java.util.List;

/**
 * @Author: yangb
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/save")
	public Boolean save(@RequestParam String name) {
		User entity = new User();
		entity.setName(name);
		return userService.save(entity);
	}

	@GetMapping("/list")
	public List<User> list() {
		return userService.findAll();
	}

}
