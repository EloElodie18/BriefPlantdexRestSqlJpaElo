package com.example.plantdex.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.plantdex.model.Categorie;
import com.example.plantdex.model.Plante;
import com.example.plantdex.repository.CategorieRepository;
import com.example.plantdex.repository.PlanteRepository;

@RestController
@RequestMapping("/api/plante")
public class PlanteController {
	
	@Autowired
	private PlanteRepository planteRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@GetMapping
	public List<Plante> getAll() {
		return planteRepository.findAll();
	}
	
	@GetMapping("{id}")
	Plante getOne(@PathVariable Long id) {
		return planteRepository.findById(id).orElseThrow();
	}
	
	@PostMapping
	public void add(@RequestBody Plante plante, @RequestParam Long categorieId) {
		Categorie categorie = categorieRepository.findById(categorieId).orElseThrow();
		plante.setCategorie(categorie);
		planteRepository.save(plante);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		planteRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable Long id, @RequestBody Plante planteUpdate) {

		Plante plante = planteRepository.findById(id).orElseThrow();
		planteRepository.delete(plante);

		plante.setNom(planteUpdate.getNom());
		plante.setCategorie(planteUpdate.getCategorie());
		plante.setEnsoleillement(planteUpdate.getEnsoleillement());
		plante.setArrosage(planteUpdate.getArrosage());
		plante.setImageUrl(planteUpdate.getImageUrl());
		
		planteRepository.save(plante);
	}
}
