/**
 * 
 */
package dev.config;

import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/** 
 *
 * @author KOMINIARZ Anaïs
 *
 */

@Configuration
@PropertySource("app.properties")
//@ComponentScan({"dev.dao","dev.ihm","dev.service"})
public class DataSourceConfig {

	@Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
	
	@Bean
	public DataSource dataSource(
			@Value("${bdd.driver}") String driver,
			@Value("${bdd.user}") String utilisateur,
			@Value("${bdd.pass}") String motDePasse,
			@Value("${bdd.url}") String url
	) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		
		dataSource.setUrl(url);
		dataSource.setUsername(utilisateur);
		dataSource.setPassword(motDePasse);
		
		return dataSource;
	}
}
