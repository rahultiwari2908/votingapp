package com.byjus.springboot.web.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.byjus.springboot.web.model.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long>{
	List<Vote> findByUser(String user);

	List<Vote> findByTitle(String title);
	
	List<Vote> findAll();
	
}
