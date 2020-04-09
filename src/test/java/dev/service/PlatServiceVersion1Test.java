/**
 * 
 */
package dev.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.dao.IPlatDao;

/** Test de la classe PlatServiceVersion1
 *
 * @author KOMINIARZ Anaïs
 *
 */
public class PlatServiceVersion1Test {

	IPlatDao platDao;
	IPlatService platServiceVersion1;
	
	@BeforeEach
	public void setUp() {
		platDao = mock(IPlatDao.class);
		platServiceVersion1 = new PlatServiceVersion1(platDao);
	}
	
	@Test
	void testAjouterPlatNomInvalide() {
		
		assertThatThrownBy(() -> {
			platServiceVersion1.ajouterPlat("a", 2500);
		}).hasMessage("un plat doit avoir un nom de plus de 3 caractères");
	}
	
	@Test
	void testAjouterPlatPrixInvalide() {
		
		assertThatThrownBy(() -> {
			platServiceVersion1.ajouterPlat("moulesfrites",10);
		}).hasMessage("le prix d'un plat doit être supérieur à 5 €");
		
	}
	
	@Test
	void testAjouterPlat() {
		
		platServiceVersion1.ajouterPlat("moulesfrites", 1200);
		verify(platDao).ajouterPlat("moulesfrites",1200);	
	}
	
}
