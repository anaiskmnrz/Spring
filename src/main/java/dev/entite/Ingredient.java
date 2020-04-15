/**
 * 
 */
package dev.entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/** Représentation d'un ingrédient d'un plat
 *
 * @author KOMINIARZ Anaïs
 *
 */
@Entity 
public class Ingredient {

	
	@Id
	private Integer id;
	
	private String nom;
	
	@ManyToMany
	@JoinTable(name="plat_ingredient",
					joinColumns=@JoinColumn(name="ingredient_id",referencedColumnName="id"),
					inverseJoinColumns=@JoinColumn(name="plat_id", referencedColumnName="id")
	)
	private List<Plat> plats;
	
	/** Getter
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/** Setter
	 *
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** Getter
	 *
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter
	 *
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter
	 *
	 * @return the plats
	 */
	public List<Plat> getPlats() {
		return plats;
	}

	/** Setter
	 *
	 * @param plats the plats to set
	 */
	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}
}
