package testedev.service.com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterCHain(HttpSecurity ender) throws Exception {
		ender.csrf()
		.disable()
		.authorizeHttpRequests()
		.antMatchers(" \"/api/v1/auth/**\",\r\n"
				+ "\"/v2/api-docs\",\r\n"
				+ "\"/v3/api-docs\",\r\n"
				+ "\"/v3/api-docs/**\",\r\n"
				+ "\"/swagger-resources\",\r\n"
				+ "\"/swagger-resources/**\",\r\n"
				+ "\"/configuration/ui\",\r\n"
				+ " \"/configuration/security\",\r\n"
				+ "\"/swagger-ui/**\",\r\n"
				+ " \"/webjars/**\",\r\n"
				+ "\"/swagger-ui.html\"")
		.permitAll();
		 //.antMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name());
		
		
		
		
		return ender.build();
	}

}
