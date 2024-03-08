package com.example.plantdex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.plantdex.model.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, Long>  {
	Categorie findByIdEquals(Long IdExact);
	Categorie findByLibelle(String LibelleExact);
}
