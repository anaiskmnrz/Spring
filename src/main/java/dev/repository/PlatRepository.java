/**
 * 
 */
package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Plat;

/** 
 *
 * @author KOMINIARZ Anaïs
 *
 */
public interface PlatRepository extends JpaRepository<Plat, Integer>{

	@Query("SELECT p FROM Plat p WHERE p.prix = :prixPlat")
	List<Plat> findByPrix(@Param("prixPlat")Integer prix);
	
	@Query("SELECT AVG(p.prix) FROM Plat p WHERE p.prix >:prixMin")
	Double avgPrix(@Param("prixMin")Integer prix);
	//TODO 
	//@Query(" SELECT i FROM Ingredient INNER JOIN Plat on i.ingredient_id = :plat.getId() ")
	//List<Ingredient> findByNomWithIngredients(@Param);
	
	void changerNom(String ancienNom, String nouveauNom)
}
