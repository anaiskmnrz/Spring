package dev.entite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity 
public class Plat {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
    private String nom;
    private Integer prix;
    
    @ManyToMany(mappedBy="plats")
    private List<Ingredient> ingredients;


    public Plat() {
    }

    public Plat(String nom, Integer prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plat plat = (Plat) o;
        return nom.equals(plat.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

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
	 * @return the prix
	 */
	public Integer getPrix() {
		return prix;
	}

	/** Setter
	 *
	 * @param prix the prix to set
	 */
	public void setPrix(Integer prix) {
		this.prix = prix;
	}
}
