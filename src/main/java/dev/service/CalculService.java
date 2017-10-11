/**
 * 
 */
package dev.service;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.exception.CalculException;
/**
 * @author ETY9
 *
 */
public class CalculService {
	private static final Logger LOG = LoggerFactory.getLogger(CalculService.class);

	public Optional<Integer> additionner(String expression) {
		String[] content = expression.split("\\+");

		Optional<Integer> somme;
		somme = Stream.of(content).map(s -> Integer.parseInt(s)).reduce((t1,t2)->t1+t2);
		return somme;
	}
}
