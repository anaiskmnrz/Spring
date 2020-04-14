/**
 * 
 */
package dev.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Plat;

/** Représentation 
 *
 * @author KOMINIARZ Anaïs
 *
 */
@Repository
@Profile("jpa")
public class PlatDaoJpa implements IPlatDao {

	private EntityManager em; 
	
	@Override
	public List<Plat> listerPlats() {
		// TODO Auto-generated method stub
		return em.createQuery("select p from Plat p", Plat.class).getResultList();
	}

	@Override
	@Transactional
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		Plat plat = new Plat(nomPlat,prixPlat);
		em.persist(plat);
	}
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
