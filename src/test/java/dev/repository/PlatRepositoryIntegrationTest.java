/**
 * 
 */
package dev.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
import org.springframework.transaction.annotation.Transactional;

import dev.config.DataSourceH2TestConfig;
import dev.config.JpaConfig;
import dev.entite.Plat;
import dev.exception.PlatException;

/** Tests de repository 
 *
 * @author KOMINIARZ Anaïs
 *
 */
@ActiveProfiles("jpa")
@SpringJUnitConfig({JpaConfig.class})
@Import(DataSourceH2TestConfig.class)
//@Transactional peut mettre ici et ça s'applique à tous
public class PlatRepositoryIntegrationTest {
	
	@Autowired
	PlatRepository repository;
	
	//pour les tests on peut faire le constructeur pour injection de dépendance? 

	//private static final Logger LOGGER = Logger.getLogger(PlatRepositoryIntegrationTest.class.getName());
	 
	@Test
	void testFindAll() {
		assertFalse(repository.findAll().isEmpty());
		
	}
	
	@Test
	void testFindAllSortAsc() {
		/*Sort tri = Sort.sort(Plat.class)
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

		assertThat(listeAjoutNonTriee).isNotEqualTo(listeRecup);*/
		Sort sort = Sort.sort(Plat.class).by(Plat::getPrix).ascending();

		assertThat(repository.findAll(sort))
				.isSortedAccordingTo(Comparator.comparing(Plat::getPrix));
	}
	
	@Test
	void testFindAllSortDesc() {
		
		/*Sort tri = Sort.sort(Plat.class)
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

		assertThat(listeAjoutNonTriee).isNotEqualTo(listeRecup);*/
		
		Sort sort = Sort.sort(Plat.class).by(Plat::getPrix).descending();

		assertThat(repository.findAll(sort))
				.isSortedAccordingTo(Comparator.comparing(Plat::getPrix).reversed());
		
	}
	
	
	//containsExactly compare que le nom aussi ?? 
	@Test
	void testFindAllPageable() {

		Pageable page = PageRequest.of(0, 2, Sort.by("nom").ascending());
	
		assertThat(repository.findAll(page)).containsExactly(new Plat("Blanquette de veau", 1500), 
			new Plat("Couscous",1600));
		/*Page<Plat> platPagination = repository.findAll(page);
	
		assertThat(platPagination.getContent().get(0).getNom()).isEqualTo("Blanquette de veau");
		assertThat(platPagination.getContent().get(1).getNom()).isEqualTo("Couscous");
		 */
	}
	
	@Test
	void testFindById() {
		Optional<Plat> findById = repository.findById(2);
		
		// Optionnal<Plat>
		Plat platRecup = findById.orElseThrow(() -> new PlatException());
		Plat platAjout = new Plat("Moules-frites",1300);
		
		assertThat(platRecup).isEqualTo(platAjout);
		
		// correction : assertThat(platRepo.findById(1)).hasValue(new Plat("Magret de canard", 0));
	}
	
	@Test 
	@Transactional
	void testGetOne() {
		
		Plat platRecup = repository.getOne(1);
		Plat platAjout = new Plat("Magret de canard",1300);
		
		assertThat(platRecup).isEqualTo(platAjout);
	}
	
	@Test
	void testCount() {
		assertThat(repository.count()).isEqualTo(6);
	}

	@Test
	void testFindByPrix() {
		Plat platAjout = new Plat("Couscous",1600);
		List<Plat> listeRecup = repository.findByPrix(1600);
		assertTrue(listeRecup.contains(platAjout));
	}
	
	@Test
	void testAvgprix() {
		Double avgRecup = repository.avgPrix(1600);
		assertThat(avgRecup).isEqualByComparingTo(2500.0);
		
		// correction avec BigDecimal et un arrondi
		//BigDecimal result = platRepo.avgPrix(1300).setScale(2, RoundingMode.UP);
		//assertThat(result).isEqualTo(new BigDecimal("1866.67"));
	}
	
	// Pas de transactional car on utilise EntityGraph
	// récupérer un plat et pouvoir aussi récupérer ses ingrédients
	@Test
	void testFindByNomWithIngredients() {
		Plat plat = repository.findByNomWithIngredients("Magret de canard");
		assertThat(plat.getIngredients()).hasSize(5);
	}
	
	@Test
	@Transactional // pour le rollback
	void testSave() {
		Plat plat = new Plat("Saumon", 1400);
		repository.save(plat);
		assertTrue(repository.findAll().contains(plat));
	}
	
//.contains juste sur les noms?
	@Transactional
	@Test
	void testChangerNom() {
		repository.changerNom("Couscous", "Couscous epice");
		assertThat(repository.findAll()).contains(new Plat("Couscous epice", 1600));
		assertThat(repository.findAll()).doesNotContain(new Plat("Couscous",1600));
	}
}
