package com.rtr;

import com.rtr.model.*;
import com.rtr.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class RtrApplication {

	public static void main(String[] args) {
		SpringApplication.run(RtrApplication.class, args);
	}

	@Bean
	CommandLineRunner start(OperateurService operateurService, EtatService etatService, StructureService structureService, PrestationService prestationService, UserService user){
		return  args -> {

			user.saveUser(new User("tresor","tresor","jacksossou9@gmail.com","tresor",1, Role.ADMIN));
			//user.saveUser(new User( "plaignant@gmail.com","1234",1, Role.PLAIGNANT ));
			//user.saveUser(new User( "user@gmail.com","67",1, Role.USER ));

			etatService.saveEtat(new Etat(null,"envoyer"));
			etatService.saveEtat(new Etat(null,"encours"));
			etatService.saveEtat(new Etat(null,"accepter"));
			etatService.saveEtat(new Etat(null,"rejeter"));
			etatService.saveEtat(new Etat(null,"rembourser"));
			etatService.saveEtat(new Etat(null,"non rembourser"));
			structureService.saveStructure(new Structure("DAGL", "sdvsdvvds", "DAGL"));
			structureService.saveStructure(new Structure("ATD", "sdvsdvvds", "ATD"));
			Structure dgdn = structureService.saveStructure(new Structure("DGDN", "sdvsdvvds", "DGDN"));
			prestationService.savePrestation(new Prestation( "Demande de passport" ,100000,dgdn));
			prestationService.savePrestation(new Prestation( "Carte de séjour" ,50000,dgdn));
			prestationService.savePrestation(new Prestation( "Permis de construire" ,20000,dgdn));

			operateurService.saveOperateur(new Operateur("T-Money"));
			operateurService.saveOperateur(new Operateur("Flooz"));
			operateurService.saveOperateur(new Operateur("Visa"));



		};
	}

}
