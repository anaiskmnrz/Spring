/**
 * 
 */
package dev;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.ihm.Menu;

/** Représentation 
 *
 * @author KOMINIARZ Anaïs
 *
 */
public class AppSpringXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-config-fichier.xml");
		
		// récupération du bean Menu
		Menu menu = context.getBean(Menu.class);
		
		// démarrage de l'application
		menu.afficher();
		
		//fermeture du Scanner
		context.getBean(Scanner.class).close();
		
		//fermeture du contexte Spring
		context.close();

	}

}
