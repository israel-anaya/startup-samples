package org.startupsamples.ms.ts.customer;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.startupframework.security.CustomizerAntMatchers;
import org.startupframework.security.StartupSecurityConfig;
import org.startupsamples.dm.customer.def.CustomerDef;

@Configuration
@EnableWebSecurity
@Profile(value = { "local", "dev", "qa", "uat", "prod" })
public class SecurityConfig extends StartupSecurityConfig {

	@Override
	protected void customAntMatchers(CustomizerAntMatchers customizer) {

		customizer.antMatchers(HttpMethod.GET, CustomerDef.CUSTOMER_PATH + "/**").hasAuthority("SCOPE_read");
		customizer.antMatchers(HttpMethod.POST, CustomerDef.CUSTOMER_PATH + "/**").hasAuthority("SCOPE_write");
		customizer.antMatchers(HttpMethod.PUT, CustomerDef.CUSTOMER_PATH + "/**").hasAuthority("SCOPE_write");
		customizer.antMatchers(HttpMethod.DELETE, CustomerDef.CUSTOMER_PATH + "/**").hasAuthority("SCOPE_write");

	}

}
