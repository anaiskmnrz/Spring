/**
 * 
 */
package dev.config;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/** Représentation de la configuration de l'application
 *
 * @author KOMINIARZ Anaïs
 *
 */
//Le stéréotype @Configuration précise que cette classe servira de configuration
@Configuration
@ComponentScan({"dev.ihm","dev.service","dev.dao"})
@PropertySource("app.properties")
@Import({DataSourceConfig.class, JpaConfig.class})
public class AppConfig {

	@Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
