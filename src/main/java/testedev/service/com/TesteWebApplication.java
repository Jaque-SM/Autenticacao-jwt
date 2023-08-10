package testedev.service.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SpringBootApplication
public class TesteWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteWebApplication.class, args);
	}
	
	
}
