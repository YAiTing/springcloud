package com.yb.user.repository;

import com.yb.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: yangb
 * @Description:
 */
@Repository
public class UserRepository {

	private Map<Long, User> data = new ConcurrentHashMap<>();

	private static final AtomicLong idGenerator = new AtomicLong(0);

	public boolean save(User entity) {
		Long id = idGenerator.incrementAndGet();
		entity.setId(id);
		data.putIfAbsent(id, entity);
		return true;
	}


	public List<User> findAll() {
		return new ArrayList<>(data.values());
	}

}
