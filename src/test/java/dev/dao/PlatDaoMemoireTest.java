/**
 * 
 */
package dev.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Test de la classe PlatMemoireTest
 *
 * @author KOMINIARZ Anaïs
 *
 */
public class PlatDaoMemoireTest {

	private PlatDaoMemoire platDaoMemoire;
	
	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}
	
	@Test
	void listerPlatsVideALInitialisation() {
		
		assertTrue(platDaoMemoire.listerPlats().isEmpty());
	}
	
	@Test
	void ajouterPlatCasPassants() {
		platDaoMemoire.ajouterPlat("moulesfrites", 1500);
		
		assertFalse(platDaoMemoire.listerPlats().isEmpty());
	}
}
