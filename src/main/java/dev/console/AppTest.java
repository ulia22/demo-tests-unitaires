package dev.console;

import dev.exception.CalculException;
import dev.service.CalculService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AppTest {

	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	private App app;
	private CalculService calculService;
	private static final Logger LOG = LoggerFactory.getLogger(AppTest.class);
	@Rule
	public final TextFromStandardInputStream forceScanner = TextFromStandardInputStream.emptyStandardInputStream();


	@Before
	public void setUp() throws Exception {
		Scanner sc = new Scanner(System.in);
		//this.calculService = mock(CalculService.class);
		this.calculService = new CalculService();
		this.app = new App(sc, calculService);
	}

	@Test
	public void testAfficherTitre() throws Exception {
		this.app.afficherTitre();
		String logConsole = systemOutRule.getLog();
		assertThat(logConsole).contains("**** Application Calculatrice ****");

	}

	@Test
	public void testEvaluer() throws Exception {
		LOG.info("Etant donné, un service CalculService qui retourne 35 à l'évaluation de l'expression 1+34");
		String expression = "1+34";
		when(calculService.additionner(expression)).thenReturn(Optional.of(35));
		LOG.info("Lorsque la méthode evaluer est invoquée");
		this.app.evaluer(expression);
		LOG.info("Alors le service est invoqué avec l'expression {}", expression);
		verify(calculService).additionner(expression);
		assertThat(systemOutRule.getLog()).contains("1+34=35");
	}

	@Test
	public void testExpressionInvalide() throws Exception {
		LOG.info("Etant donné, un service CalculService qui throw une CalculException");
		String expression = "1+34hkjlh";
		try{
			this.app.evaluer(expression);
		}catch(CalculException ce){
			String msg2 = "L’expression "+expression+" est invalide.";
			LOG.info(msg2);
		}
		String msg = "L’expression "+expression+" est invalide.";
		assertThat(systemOutRule.getLog()).contains(msg);
	}

	@Test
	public void testEtape1()throws Exception{
		forceScanner.provideLines("fin");
		this.app.demarrer();
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}

	@Test
	public void testEtape2()throws Exception{
		forceScanner.provideLines("1+2", "fin");
		this.app.demarrer();
		assertThat(systemOutRule.getLog()).contains("**** Application Calculatrice ****");
		assertThat(systemOutRule.getLog()).contains("Veuillez saisir une expression :");
		assertThat(systemOutRule.getLog()).contains("1+2=3");
		assertThat(systemOutRule.getLog()).contains("Veuillez saisir une expression :");
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}
	
	@Test
	public void testEtape3()throws Exception{
		forceScanner.provideLines("AAAAA", "fin");
		this.app.demarrer();
		assertThat(systemOutRule.getLog()).contains("**** Application Calculatrice ****");
		assertThat(systemOutRule.getLog()).contains("Veuillez saisir une expression :");
		assertThat(systemOutRule.getLog()).contains("L'expression AAAAA est invalide.");
		assertThat(systemOutRule.getLog()).contains("Veuillez saisir une expression :");
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}
	
	@Test
	public void testEtape4()throws Exception{
		forceScanner.provideLines("1+2","30+2", "fin");
		this.app.demarrer();
		assertThat(systemOutRule.getLog()).contains("**** Application Calculatrice ****");
		assertThat(systemOutRule.getLog()).contains("Veuillez saisir une expression :");
		assertThat(systemOutRule.getLog()).contains("1+2=3");
		assertThat(systemOutRule.getLog()).contains("Veuillez saisir une expression :");
		assertThat(systemOutRule.getLog()).contains("30+2=3");
		assertThat(systemOutRule.getLog()).contains("Veuillez saisir une expression :");
		assertThat(systemOutRule.getLog()).contains("Aurevoir :-(");
	}
}