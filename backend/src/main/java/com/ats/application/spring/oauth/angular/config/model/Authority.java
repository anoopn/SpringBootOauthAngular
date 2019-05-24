package com.ats.application.spring.oauth.angular.config.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AUTHORITY", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
@NoArgsConstructor
@Data
public class Authority implements GrantedAuthority {

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Override
	public String getAuthority() {
		return this.name;
	}
}
