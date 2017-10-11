/**
 * 
 */
package dev.console;

import dev.service.CalculService;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ETY9
 *
 */
public class App {
	private Scanner scanner;
	private CalculService calculatrice;
	
	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);
	
	public App(Scanner scanner, CalculService calculatrice) {
		this.scanner = scanner;
		this.calculatrice = calculatrice;
	}
	protected void afficherTitre() {
		LOG.info("**** Application Calculatrice ****");
		
	}
	public void demarrer() {
		afficherTitre();
		String input = "";
		while(!input.equals("fin")){
			LOG.info("Veuillez saisir une expression :");
			input = scanner.nextLine();
			evaluer(input);
		}
		LOG.info("Aurevoir :-(");
	}
	protected void evaluer(String expression) {
		if(expression.equals("fin")){
			return;
		}
		try{
		LOG.info("{}={}", expression, calculatrice.additionner(expression).get());
		}catch(Exception e){
			LOG.info("L'expression "+expression+" est invalide.");
		}
	}
}
