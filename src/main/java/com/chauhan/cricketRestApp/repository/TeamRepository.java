package com.chauhan.cricketRestApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chauhan.cricketRestApp.entity.Team;
 
public interface TeamRepository  extends JpaRepository<Team, Long> {
		
	Team findByName(String name);
}
