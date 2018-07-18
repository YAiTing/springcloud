package com.yb.user.repository;

import com.yb.user.entity.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: yangb
 * @Description:
 */
@Repository
public class PersonRepository {

	private Map<Long, Person> data = new ConcurrentHashMap<>();

	private static final AtomicLong idGenerator = new AtomicLong(0);

	public Long save(Person entity) {
		Long id = idGenerator.incrementAndGet();
		entity.setId(id);
		data.putIfAbsent(id, entity);
		return id;
	}


	public List<Person> findAll() {
		return new ArrayList<>(data.values());
	}

}
