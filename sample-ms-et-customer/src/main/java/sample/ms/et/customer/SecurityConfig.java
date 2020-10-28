package sample.ms.et.customer;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.startupframework.security.CustomizerAntMatchers;
import org.startupframework.security.StartupSecurityConfig;

@Configuration
public class SecurityConfig extends StartupSecurityConfig {

	@Override
	protected void customAntMatchers(CustomizerAntMatchers customizer) {
		customizer.antMatchers(HttpMethod.GET, "/v1.0/customers/**").hasAuthority("SCOPE_read");
		customizer.antMatchers(HttpMethod.POST, "/v1.0/customers/**").hasAuthority("SCOPE_write");
		customizer.antMatchers(HttpMethod.PATCH, "/v1.0/customers/**").hasAuthority("SCOPE_write");		
	}

}
