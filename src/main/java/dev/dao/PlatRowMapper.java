/**
 * 
 */
package dev.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.entite.Plat;

/** Représentation 
 *
 * @author KOMINIARZ Anaïs
 *
 */
public class PlatRowMapper implements RowMapper<Plat> {

	@Override
    public Plat mapRow(ResultSet rs, int rowNum) throws SQLException {
        Plat plat = new Plat();
        plat.setId(rs.getInt("id"));
        plat.setNom(rs.getString("nom"));
        plat.setPrix(rs.getInt("prix"));
        return plat;
    }
	
}
