/**
 * 
 */
package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.DataSourceH2TestConfig;
import dev.config.JpaConfig;
import dev.entite.Plat;

/** Représentation 
 *
 * @author KOMINIARZ Anaïs
 *
 */
@ActiveProfiles("jpa")
@SpringJUnitConfig({JpaConfig.class})
@Import(DataSourceH2TestConfig.class)
public class PlatRepositoryIntegrationTest {
	
	@Autowired
	PlatRepository repository;

	 private static final Logger LOGGER = Logger.getLogger(PlatRepositoryIntegrationTest.class.getName());
	
	@Test
	void testFindAll() {
		assertFalse(repository.findAll().isEmpty());
		
	}
	
	@Test
	void testFindAllSortAsc() {
		Sort tri = Sort.sort(Plat.class)
				.by(Plat::getPrix)
				.ascending();
		
		List<Plat> listeRecup= repository.findAll(tri);
		
		List<Plat> listeAjout = new ArrayList<>();
		listeAjout.add(new Plat("Côte de boeuf",1100));
		listeAjout.add(new Plat("Magret de canard",1300));
		listeAjout.add(new Plat("Moules-frites",1300));
		listeAjout.add(new Plat("Blanquette de veau",1500));
		listeAjout.add(new Plat("Couscous",1600));
		listeAjout.add(new Plat("Gigot d'agneau",2500));
		
		assertThat(listeRecup).isEqualTo(listeAjout);
		
		List<Plat> listeAjoutNonTriee = new ArrayList<>();
		listeAjoutNonTriee.add(new Plat("Côte de boeuf",1100));
		listeAjoutNonTriee.add(new Plat("Magret de canard",1300));
		listeAjoutNonTriee.add(new Plat("Moules-frites",1300));
		listeAjoutNonTriee.add(new Plat("Blanquette de veau",1500));
		listeAjoutNonTriee.add(new Plat("Gigot d'agneau",2500));
		listeAjoutNonTriee.add(new Plat("Couscous",1600));

		assertThat(listeAjoutNonTriee).isNotEqualTo(listeRecup);
		
	}
	
	@Test
	void testFindAllSortDesc() {
		
		Sort tri = Sort.sort(Plat.class)
				.by(Plat::getPrix)
				.descending();
		
		List<Plat> listeRecup= repository.findAll(tri);
		
		List<Plat> listeAjout = new ArrayList<>();
		
		listeAjout.add(new Plat("Gigot d'agneau",2500));
		listeAjout.add(new Plat("Couscous",1600));
		listeAjout.add(new Plat("Blanquette de veau",1500));
		listeAjout.add(new Plat("Magret de canard",1300));
		listeAjout.add(new Plat("Moules-frites",1300));
		listeAjout.add(new Plat("Côte de boeuf",1100));
		
		
		assertThat(listeRecup).isEqualTo(listeAjout);
		
		List<Plat> listeAjoutNonTriee = new ArrayList<>();
		listeAjoutNonTriee.add(new Plat("Côte de boeuf",1100));
		listeAjoutNonTriee.add(new Plat("Magret de canard",1300));
		listeAjoutNonTriee.add(new Plat("Moules-frites",1300));
		listeAjoutNonTriee.add(new Plat("Blanquette de veau",1500));
		listeAjoutNonTriee.add(new Plat("Gigot d'agneau",2500));
		listeAjoutNonTriee.add(new Plat("Couscous",1600));

		assertThat(listeAjoutNonTriee).isNotEqualTo(listeRecup);
		
	}
	
	@Test
	void testFindAllPageable() {

	Pageable page = PageRequest.of(0, 2, Sort.by("nom").ascending());
	Page<Plat> platPagination = repository.findAll(page);
	
	assertThat(platPagination.getContent().get(0).getNom()).isEqualTo("Blanquette de veau");
	assertThat(platPagination.getContent().get(1).getNom()).isEqualTo("Couscous");
	
	}
	

	

}
