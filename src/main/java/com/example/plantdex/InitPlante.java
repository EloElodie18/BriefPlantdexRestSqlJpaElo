package com.example.plantdex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.example.plantdex.model.Plante;
import com.example.plantdex.model.Categorie;
import com.example.plantdex.model.Ensoleillement;
import com.example.plantdex.repository.CategorieRepository;
import com.example.plantdex.repository.PlanteRepository;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class InitPlante implements ApplicationRunner {
	
	@Autowired
	private PlanteRepository planteRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {	
		
		if (args.getNonOptionArgs().contains("clean")) {
			log.info("Option clean : nettoyage des données");
			planteRepository.deleteAll();
			categorieRepository.deleteAll();
		}

		if (args.getNonOptionArgs().contains("demo")) {
			log.info("Option demo : nettoyage des données et création de données");
			planteRepository.deleteAll();
			categorieRepository.deleteAll();
			
			Categorie arbres = new Categorie("arbres");
			Categorie buissons = new Categorie("buissons");
			categorieRepository.save(arbres);
			categorieRepository.save(buissons);
			
			Ensoleillement moyenEnsoleillement = Ensoleillement.MOYEN;
		    Ensoleillement beaucoupEnsoleillement = Ensoleillement.BEAUCOUP;
		    Ensoleillement peuEnsoleillement = Ensoleillement.PEU;
			
			planteRepository.save(new Plante("chene", arbres, moyenEnsoleillement, 2, "photo de mon chene"));
			planteRepository.save(new Plante("platane", arbres, beaucoupEnsoleillement, 0, "photo de mon platane"));
			planteRepository.save(new Plante("murier", buissons, peuEnsoleillement, 0, "photo de mon murier"));
			planteRepository.save(new Plante("abélie", buissons, moyenEnsoleillement, 3, "phot de mon abelie"));
		}
	
	}

}