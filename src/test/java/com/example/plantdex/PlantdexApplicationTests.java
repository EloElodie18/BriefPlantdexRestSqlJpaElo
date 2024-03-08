package com.example.plantdex;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.plantdex.model.Categorie;
import com.example.plantdex.model.Ensoleillement;
import com.example.plantdex.model.Plante;
import com.example.plantdex.repository.CategorieRepository;
import com.example.plantdex.repository.PlanteRepository;

@SpringBootTest
class PlantdexApplicationTests {

	@Autowired
	private PlanteRepository planteRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@BeforeEach void clean() {
		planteRepository.deleteAll();
		categorieRepository.deleteAll();
	}
	
	@Test
	void contextLoads() {
		categorieRepository.save(new Categorie("fleur"));
		assertEquals("fleur", categorieRepository.findByLibelle("fleur").getLibelle());
	}	
	
	@Test void nbrCat() { 
		categorieRepository.save(new Categorie("sechés"));
		categorieRepository.save(new Categorie("grasses"));
		categorieRepository.save(new Categorie("moches"));
		
		assertEquals(3, categorieRepository.count());
	   }
	 
	@Test
	void contextLoadsPlante() {
		//Ensoleillement moyenEnsoleillement = Ensoleillement.MOYEN;
	    Ensoleillement beaucoupEnsoleillement = Ensoleillement.BEAUCOUP;	    
	    //Ensoleillement peuEnsoleillement = Ensoleillement.PEU;
		Categorie fleur = new Categorie("fleur");
		categorieRepository.save(fleur);
		//Categorie plantesVertes = new Categorie("plantesVertes");
		//Categorie cactus = new Categorie("cactus");
		planteRepository.save(new Plante("pivoine", fleur, beaucoupEnsoleillement, 2, "photo pivoine"));
		
		assertEquals("pivoine", planteRepository.findByNom("pivoine").getNom());		
	}	
	
	@Test void nbrPlante() {
		Ensoleillement moyenEnsoleillement = Ensoleillement.MOYEN;
	    Ensoleillement beaucoupEnsoleillement = Ensoleillement.BEAUCOUP;
	    Ensoleillement peuEnsoleillement = Ensoleillement.PEU;
	    Categorie fleur = new Categorie("fleur");
	    categorieRepository.save(fleur);
		Categorie plantesVertes = new Categorie("plantesVertes");
		categorieRepository.save(plantesVertes);
		planteRepository.save(new Plante("aloe", fleur, peuEnsoleillement, 0, "photo aloe"));
		planteRepository.save(new Plante("palmira", plantesVertes, moyenEnsoleillement, 2, "photo palmira"));
		planteRepository.save(new Plante("phalaenopsys", fleur, beaucoupEnsoleillement, 2, "photo phalaenopsys"));
		
		assertEquals(3, planteRepository.count());
	   }
	

}
