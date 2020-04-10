/**
 * 
 */
package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.entite.Plat;

/** Test de PlatDaoFichier
 *
 * @author KOMINIARZ Anaïs
 *
 */

@SpringJUnitConfig(PlatDaoFichier.class)
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlatDaoFichierTest {
	
	@Autowired
	PlatDaoFichier platDaoFichier;

	@Test
	void testAjouterPlatCasPassant() {
		
		List<Plat> liste = new ArrayList<>();
		Plat plat = new Plat("couscous",1800);
		liste.add(plat);
		
		platDaoFichier.ajouterPlat("couscous", 1800);
		//pour tester qu'on a bien ajouté UN fichier et exactement le bon
		assertThat(platDaoFichier.listerPlats()).isEqualTo(liste);
		// pour tester que ça bien été sauvegardé
		//assertFalse(platDaoFichier.listerPlats().isEmpty());
	}
	
	// ajout de dirtiescontext afin que le contexte se recrée et que le fichier soit bien vide avant d'ajouter le plat. 
	@Test
	void testAjouterPlatIndépendant() {
		List<Plat> liste = new ArrayList<>();
		Plat plat = new Plat("couscous",1600);
		liste.add(plat);
		
		platDaoFichier.ajouterPlat("couscous", 1600);
		assertThat(platDaoFichier.listerPlats()).isEqualTo(liste);
	}
	
}
