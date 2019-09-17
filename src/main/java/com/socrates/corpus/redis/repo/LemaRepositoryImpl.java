package com.socrates.corpus.redis.repo;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.socrates.corpus.redis.model.Lema;

@Repository
public class LemaRepositoryImpl implements LemaRepository {

	private static final String KEY = "Lema";
	
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations<String, String, Lema> hashOperations;

	@Autowired
	public LemaRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	@Override
	public void save(Lema lema) {
		hashOperations.put(KEY, lema.getLemaId(), lema);

	}

	@Override
	public Lema find(String lemaId) {
		return hashOperations.get(KEY, lemaId);
	}

	@Override
	public Map<String, Lema> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public void update(Lema lema) {
		hashOperations.put(KEY, lema.getLemaId(), lema);

	}

	@Override
	public void delete(String lemaId) {
		hashOperations.delete(KEY, lemaId);
	}

}
