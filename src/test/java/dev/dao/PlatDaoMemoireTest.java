/**
 * 
 */
package dev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.entite.Plat;

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
		
		assertEquals(true,platDaoMemoire.listerPlats().isEmpty());
	}
	
	@Test
	void ajouterPlatCasPassants() {
		platDaoMemoire.ajouterPlat("moulesfrites", 1500);
		
		assertEquals(false, platDaoMemoire.listerPlats().isEmpty());
	}
}
