package dev.console;

import dev.service.CalculService;
import java.util.Scanner;

public class AppLauncher {
	
	public static void main(String[] args) {
		
		try (Scanner scanner = new Scanner(System.in)) {
			new App(scanner, new CalculService()).demarrer();
		}
	}
}