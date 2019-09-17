package com.socrates.corpus.redis.repo;

import java.util.Map;

import com.socrates.corpus.redis.model.Lema;

public interface LemaRepository {
	
	void save(Lema lema);
	Lema find(String lemaId);
	Map<String, Lema> findAll();
	void update(Lema lema);
	void delete(String lemaId);

}
