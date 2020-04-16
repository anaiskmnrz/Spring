/**
 * 
 */
package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	// Pourquoi return une liste dans la correction?
	@EntityGraph(
			attributePaths = "ingredients"
	)
	@Query(" SELECT p FROM Plat p WHERE p.nom = :nomPlat")
	Plat findByNomWithIngredients(@Param("nomPlat")String nom);
	
	@Modifying
	@Query("UPDATE Plat p SET p.nom = :nouveauNom WHERE p.nom = :ancienNom")
	void changerNom(@Param("ancienNom")String ancienNom, @Param("nouveauNom")String nouveauNom);
}
