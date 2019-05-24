package com.ats.application.spring.oauth.angular.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ats.application.spring.oauth.angular.config.model.Roles;
import com.ats.application.spring.oauth.angular.config.model.User;

public interface UserDAO {

	@Query("SELECT DISTINCT user FROM User user " + "INNER JOIN FETCH user.roles AS roles "
			+ "WHERE user.username = :username")

	User findByUsername(@Param("username") String username);

	Set<Roles> findRoles();
}
