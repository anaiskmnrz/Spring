/**
 * 
 */
package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.dao.PlatDaoMemoire;
import dev.exception.PlatException;

/** Test de PlatServiceVersion2
 *
 * @author KOMINIARZ Anaïs
 *
 */

@SpringJUnitConfig({PlatServiceVersion2.class, PlatDaoMemoire.class})
@ActiveProfiles({"memoire","service2"})
public class PlatServiceVersion2IntegrationTest {

	@Autowired
	PlatServiceVersion2 platServiceVersion2;
	
	@Test
	void testAjouterPlat() {
		platServiceVersion2.ajouterPlat("moulesfrites", 1200);
		assertFalse(platServiceVersion2.listerPlats().isEmpty());
	}
	
	@Test
	void testAjouterPlatPrixInvalide(){
		assertThatThrownBy(() -> {
			platServiceVersion2.ajouterPlat("moulesfrites", 1);
		}).isInstanceOf(PlatException.class);
	}
}
