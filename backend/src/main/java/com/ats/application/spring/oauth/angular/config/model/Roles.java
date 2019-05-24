package com.ats.application.spring.oauth.angular.config.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@NoArgsConstructor
@Data
public class Roles implements Serializable {

	@Id
	@Column(name = "role_id")
	private Integer roleId;

	@Column(name = "role")
	private String role;

}
