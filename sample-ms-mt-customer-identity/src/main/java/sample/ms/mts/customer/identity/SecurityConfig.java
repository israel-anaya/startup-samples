package sample.ms.mts.customer.identity;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.startupframework.security.CustomizerAntMatchers;
import org.startupframework.security.StartupSecurityConfig;

@Configuration
public class SecurityConfig extends StartupSecurityConfig {

	@Override
	protected void customAntMatchers(CustomizerAntMatchers customizer) {
		customizer.antMatchers(HttpMethod.POST, "/v1.0/customers-identity/**").hasAuthority("SCOPE_read");

	}

}
