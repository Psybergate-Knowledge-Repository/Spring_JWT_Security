package com.psybergate.login_page.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JPAAuditingConfig {

	@Bean
	public AuditorAware<String> auditorAware(JPAAuditingSecurityContextWrapperConfig.SecurityContextWrapper securityContextWrapper) {
		return new AuditorAware<String>() {

			@Override
			public Optional<String> getCurrentAuditor() {
				Object principal = securityContextWrapper.getContext().getAuthentication().getPrincipal();

				String username = null;

				if (principal instanceof UserDetails) {
					username = ((UserDetails) principal).getUsername();
				} else {
					username = principal.toString();
				}

				return Optional.of(username);
			}
		};
	}

}
