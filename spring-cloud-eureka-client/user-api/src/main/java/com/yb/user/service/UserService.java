package com.yb.user.service;

import com.yb.user.entity.User;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * @Author: yangb
 * @Description:
 */
public interface UserService {

	boolean save(User entity);

	List<User> findAll();
}
