package com.psybergate.login_page.user;


import com.psybergate.framework.domain.base.AbstractAuditedEntity;
import com.psybergate.login_page.role.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@SuppressWarnings("serial")
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(name = "uq_users_userName", columnNames = "userName") })
public class User extends AbstractAuditedEntity<User, String> {

	@Column(nullable = false,unique = true)
	private String userName;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	@NotNull(message = "active is mandatory")
	private Boolean active;

	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	@ManyToMany
	@Size(min=1,max=7)
	private Set<Role> roles;

	public User() {
	}

	public User(String userName, String password, Boolean active, Set<Role> roles) {
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", active=" + active + ", roles=" + roles
				+ ", toString()=" + super.toString() + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String getUniqueIdentifier() {
		return getUserName();
	}
}
