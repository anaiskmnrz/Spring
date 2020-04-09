/**
 * 
 */
package dev;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.ihm.Menu;

/** Migration de l'application vers Spring avec XML
 *
 * @author KOMINIARZ Anaïs
 *
 */
public class AppSpringXML {

	public static void main(String[] args) {
		
		// création du contexte Spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-config-fichier.xml");
		
		// récupération du bean Menu
		Menu menu = context.getBean(Menu.class);
		
		// démarrage de l'application
		menu.afficher();
		
		//fermeture du contexte Spring
		context.close();

	}

}
