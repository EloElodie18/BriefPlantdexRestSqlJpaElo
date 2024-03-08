package com.example.plantdex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.plantdex.model.Categorie;
import com.example.plantdex.model.Plante;

public interface PlanteRepository extends JpaRepository<Plante, Long> {
	Plante findByIdEquals(Long IdExact);
	List<Plante> findByCategorie(Categorie categorie); 
	Plante findByNom(String NomExact);
}
