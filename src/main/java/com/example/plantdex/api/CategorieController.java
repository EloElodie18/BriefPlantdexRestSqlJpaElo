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
import org.springframework.web.bind.annotation.RestController;
import com.example.plantdex.model.Categorie;
import com.example.plantdex.repository.CategorieRepository;


@RestController
@RequestMapping("/api/categorie")
public class CategorieController {
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@GetMapping
	public List<Categorie> getAll() {
		return categorieRepository.findAll();
	}	
	
	@GetMapping("{id}")
	Categorie getOne(@PathVariable Long id) {
		return categorieRepository.findById(id).orElseThrow();
	}
		
	@PostMapping("{id}")
	public void add(@RequestBody Categorie categorie) {
		categorieRepository.save(categorie);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		categorieRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable Long id, @RequestBody Categorie categorieUpdate) {

		Categorie categorie = categorieRepository.findById(id).orElseThrow();
		categorieRepository.delete(categorie);

		categorie.setLibelle(categorieUpdate.getLibelle());
		
		categorieRepository.save(categorie);
		}
}
