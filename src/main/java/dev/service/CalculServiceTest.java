package dev.service;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.*;
import dev.exception.CalculException;

/**
 * Test unitaire de la classe dev.service.CalculService.
 */
public class CalculServiceTest {
	private static final Logger LOG = LoggerFactory.getLogger(CalculServiceTest.class);

	@Test
	public void testAdditionner() throws Exception {
		
		LOG.info("Etant donné, une instance de la classe CalculService");
		CalculService calc = new CalculService();
		
		LOG.info("Lorsque j'évalue l'addition de l'expression 1+3+4");
		int somme = calc.additionner("1+3+4").orElse(0);
		
		LOG.info("Alors j'obtiens le résultat 8");
		assertThat(somme).isEqualTo(8);
	}
	
	@Test(expected=CalculException.class)
	public void testAdditionner2() throws Exception {
		LOG.info("Essai d'envoyer des argument impossibles");
		CalculService calc = new CalculService();
		calc.additionner("1+2+test");
	}
}