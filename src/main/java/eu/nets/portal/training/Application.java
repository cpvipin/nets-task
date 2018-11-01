package eu.nets.portal.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true) })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
