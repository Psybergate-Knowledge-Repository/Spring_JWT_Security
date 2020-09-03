package com.psybergate.spring_jwt_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class JPAAuditingSecurityContextWrapperConfig {

	@Bean
	public SecurityContextWrapper securityContextWrapper() {
		return new SecurityContextWrapper();
	}

	public class SecurityContextWrapper {
		public SecurityContext getContext() {
			return SecurityContextHolder.getContext();
		}
	}
}