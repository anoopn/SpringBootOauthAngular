package com.ats.application.spring.oauth.angular.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ats.application.spring.oauth.angular.config.model.Roles;
import com.ats.application.spring.oauth.angular.config.model.User;
import com.ats.application.spring.oauth.angular.dao.UserDAO;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements InterUserService {

	@Autowired
	UserDAO userDAO;

	@Column(name = "ACCOUNT_EXPIRED")
	private boolean accountExpired;

	@Column(name = "ACCOUNT_LOCKED")
	private boolean accountLocked;

	@Column(name = "CREDENTIALS_EXPIRED")
	private boolean credentialsExpired;

	/*@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return userDAO.findRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
				.collect(Collectors.toList());
	}
*/
	/*
	 * @PreAuthorize("hasAnyRole('ADMIN')") public List<User> findAll() { List<User>
	 * list = new ArrayList<>();
	 * userDao.findAll().iterator().forEachRemaining(list::add); return list; }
	 */

	/*@Override
	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isCredentialsExpired();
	}
*/
	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<Roles> roles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		for (Roles userRole : roles) {
			System.out.println("User Roles---" + userRole.getRole());
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}

}
