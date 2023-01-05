package com.chauhan.cricketRestApp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Teams")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Name", nullable = false)
	private String name;
	
	@Column(name = "TeamBudget", nullable = false)
	private double teamBudget;
	
	@OneToMany(targetEntity = Player.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "Team_ID_fk", referencedColumnName = "id")
	private List<Player> players;
}
