package com.psybergate.login_page.role;

import com.psybergate.framework.domain.base.AbstractAuditedEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@SuppressWarnings("serial")
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(name = "uq_roles_name", columnNames = "name"))
public class Role extends AbstractAuditedEntity<Role, String> implements GrantedAuthority {

	@Column(nullable = false,unique = true)
	@NotBlank(message = "name is mandatory")
	private String name;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", toString()=" + super.toString() + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getUniqueIdentifier() {
		return getName();
	}

	@Override
	public String getAuthority() {
		return name;
	}
}
