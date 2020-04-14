/**
 * 
 */
package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.DataSourceConfig;
import dev.config.JpaConfig;
import dev.entite.Plat;

/** Test pour la classe PlatDaoJpa
 *
 * @author KOMINIARZ Anaïs
 *
 */
@ActiveProfiles("jpa")
@SpringJUnitConfig({JpaConfig.class, PlatDaoJpa.class })
@Import(DataSourceConfig.class)
public class PlatDaoJpaTest {
	
	private static final Logger LOGGER = Logger.getLogger(PlatDaoJpaTest.class.getName());
	
	@Autowired
	private IPlatDao platDaoJpa;
	
	@PersistenceContext
	private EntityManager em; 
	
	@Test
	void testListerPlatsNonVide() {
		assertFalse(platDaoJpa.listerPlats().isEmpty());
	}
	
	@Test
	void testAjouterPlat() {
		LOGGER.info("Etant donne que l'on ajoute un plat a la table");
		Plat platAjout = new Plat("framboises",1100);
		platDaoJpa.ajouterPlat(platAjout.getNom(), platAjout.getPrix());
		
		LOGGER.info("Lorsque l'on recupere le plat ajoute a la table");
		 TypedQuery<Plat> createQuery = em.createQuery("SELECT p FROM Plat p WHERE p.nom= :nom", Plat.class);
	      createQuery.setParameter("nom", "framboises");
	  
	      Plat platRecup = createQuery.getSingleResult();
	    LOGGER.info("Alors le plat ajoute correspond au plat recupere");
		assertThat(platAjout).isEqualTo(platRecup);
	}
	

}
