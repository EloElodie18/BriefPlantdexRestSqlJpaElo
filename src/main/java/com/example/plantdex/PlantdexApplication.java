package com.example.plantdex;
import com.example.plantdex.model.Plante;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlantdexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantdexApplication.class, args);
	}
	
	/*@Bean
	List<Plante> getAll(){ //creer methode qui renvoie une liste, et elle sera capable de ns la donner
		return new ArrayList<Plante>();		
	}
	 */
}
