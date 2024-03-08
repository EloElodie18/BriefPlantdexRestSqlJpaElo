package com.example.plantdex.model;

import jakarta.persistence.Column;

//import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor

public class Plante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //= UUID.randomUUID().toString();
	
	@NonNull
	@Column(unique=true)
	private String nom;
	
	@NonNull
	@ManyToOne
	private Categorie categorie;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	private Ensoleillement ensoleillement;

	@NonNull
	private Integer arrosage;
	
	@NonNull
	private String imageUrl;
	
}
