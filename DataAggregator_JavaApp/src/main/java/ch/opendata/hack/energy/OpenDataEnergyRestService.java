package ch.opendata.hack.energy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The type Open data energy rest service.
 */
@SpringBootApplication
public class OpenDataEnergyRestService extends SpringBootServletInitializer {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(OpenDataEnergyRestService.class, args);
	}

}
