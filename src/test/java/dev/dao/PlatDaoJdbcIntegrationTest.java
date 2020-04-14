/**
 * 
 */
package dev.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.entite.Plat;

/** Tests d'intégration de PlatDaoJdbc
 *
 * @author KOMINIARZ Anaïs
 *
 */
@SpringJUnitConfig({JdbcTestConfig.class, PlatDaoJdbc.class })
@ActiveProfiles("jdbc")
public class PlatDaoJdbcIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(PlatDaoJdbcIntegrationTest.class.getName());
	
	@Autowired
	private PlatDaoJdbc platDaoJdbc;
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	@Test
	void testListerPlatsNonVide() {
		assertFalse(platDaoJdbc.listerPlats().isEmpty());
	}
	
	@Test
	void testAjouterPlat() {
		LOGGER.info("Etant donne que l'on ajoute un plat a la table");
		Plat platAjout = new Plat("fraises",1100);
		platDaoJdbc.ajouterPlat(platAjout.getNom(), platAjout.getPrix());
		
		LOGGER.info("Lorsque l'on recupere le plat ajoute a la table");
		Plat platRecup = jdbcTemplate.queryForObject("SELECT * FROM plat WHERE nom=?", new Object[] { "fraises" }, new PlatRowMapper());
	
		LOGGER.info("Alors le plat ajoute correspond au plat recupere");
		assertThat(platAjout).isEqualTo(platRecup);
	}
}
