/**
 * 
 */
package dev.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.entite.Plat;

/** Représentation 
 *
 * @author KOMINIARZ Anaïs
 *
 */
@Profile("jdbc")
@Repository
public class PlatDaoJdbc implements IPlatDao{

	private JdbcTemplate jdbcTemplate;
	
	public PlatDaoJdbc(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	@Override
	public List<Plat> listerPlats() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.query("select * from plat", new PlatRowMapper());
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		String sql = "INSERT INTO PLAT (NOM,PRIX) VALUES(?,?)";
		jdbcTemplate.update(sql, nomPlat, prixPlat);
	}

}
