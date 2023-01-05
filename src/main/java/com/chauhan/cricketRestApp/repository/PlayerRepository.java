package com.chauhan.cricketRestApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chauhan.cricketRestApp.entity.Player;
 
public interface PlayerRepository  extends JpaRepository<Player, Long> {

}
