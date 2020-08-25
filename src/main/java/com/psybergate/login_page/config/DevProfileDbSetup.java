package com.psybergate.login_page.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Profile("dev")
public class DevProfileDbSetup {

	@Autowired
	private DataSource dataSource;

	@EventListener(ApplicationReadyEvent.class)
	public void config() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		try {
			jdbcTemplate.queryForObject("select role_id from users_roles where role_id=1", Long.class);
		} catch (DataAccessException e) {
			jdbcTemplate.update(
					"insert into users (version, user_name, password, active) values(0,'admin', '$2a$10$AmGJfEBsxd5Yw6/xsn3SeutywFu6BW8hLPTsBAO9mzSsSwzOapgFO', true);"
							+ " insert into roles (version, name) values (0, 'ROLE_ADMIN');"
							+ "  insert into users_roles (version, user_id, role_id) values (0, 1, 1)");
		}

	}

}