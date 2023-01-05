package com.chauhan.cricketRestApp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Players")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Name", nullable = false)
	private String name;
	
	@Column(name = "Age", nullable = false)
	private int age;
	
	@Column(name = "PlayerType", nullable = false)
	private String playerType;
	
	@Column(name = "cost", nullable = false)
	private double cost;
}
