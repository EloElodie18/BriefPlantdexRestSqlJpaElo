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
	void catDeCategorie() {//verifie si categorie specifiée est bien égale à la categorie comparée
		categorieRepository.save(new Categorie("fleur"));
		assertEquals("fleur", categorieRepository.findByLibelle("fleur").getLibelle());
		assertEquals("Fleur", categorieRepository.findByLibelle("fleur").getLibelle());//false car sensible à la casse
	}	
	
	@Test void nbrCat() { //nbr de categorie ds mon repo est bien égal au nbr spécifié
		categorieRepository.save(new Categorie("sechés"));
		categorieRepository.save(new Categorie("grasses"));
		categorieRepository.save(new Categorie("moches"));
		
		assertEquals(3, categorieRepository.count());
	   }
	 
	/**
	 * 
	 */
	@Test
	void Plante() {
		
		Ensoleillement moyenEnsoleillement = Ensoleillement.MOYEN;
	    Ensoleillement beaucoupEnsoleillement = Ensoleillement.BEAUCOUP;	    
	    Ensoleillement peuEnsoleillement = Ensoleillement.PEU;
		Categorie fleur = new Categorie("fleur");		
		Categorie plantesVertes = new Categorie("plantesVertes");
		Categorie cactus = new Categorie("cactus");
		categorieRepository.save(fleur);
		categorieRepository.save(plantesVertes);
		categorieRepository.save(cactus);
		
		//creation d'un objet plante
		planteRepository.save(new Plante("pivoine", fleur, beaucoupEnsoleillement, 2, "photo pivoine"));				
		
		assertEquals("pivoine", planteRepository.findByNom("pivoine").getNom());
		
		//false car sensible à la casse
		/* assertEquals("Pivoine", planteRepository.findByNom("pivoine").getNom()); */
		
		//verifier si ma plante a bien cette arrosage et cet ensoleillement
		assertEquals(2, planteRepository.findByArrosage(2).getArrosage()); 			
		assertEquals(beaucoupEnsoleillement, planteRepository.findByEnsoleillement(beaucoupEnsoleillement).getEnsoleillement());
		 
	}	
	
	@Test void nbrPlanteEtCategorie() {
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
		
		//nbr plante ds mon repo
		assertEquals(3, planteRepository.count()); 
		
		//nbr plante ds mon repo
		assertEquals(2, planteRepository.findByCategorie(categorieRepository.findByLibelle("fleur")).size());//nbr plante ds mon repo
	   }
}
