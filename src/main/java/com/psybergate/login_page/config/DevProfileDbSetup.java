package com.psybergate.login_page.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

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
					"insert into users (id, version, user_name, password, active) values(1, 0,'admin', '$2a$10$AmGJfEBsxd5Yw6/xsn3SeutywFu6BW8hLPTsBAO9mzSsSwzOapgFO', true)");

			jdbcTemplate.update("insert into users_roles values (1, 0, 1, 1)");
		}

	}

}
