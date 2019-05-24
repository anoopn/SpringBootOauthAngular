package com.ats.application.spring.oauth.angular.dao;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ats.application.spring.oauth.angular.config.model.Roles;
import com.ats.application.spring.oauth.angular.config.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUsername(String username) {
		Criteria ctrUser = sessionFactory.getCurrentSession().createCriteria(User.class);
		ctrUser.add(Restrictions.eq("username", username));
		return (User) ctrUser.uniqueResult();
	}

	@Override
	public Set<Roles> findRoles() {
		Criteria ctrRoles = sessionFactory.getCurrentSession().createCriteria(Roles.class);
		return (Set<Roles>) ctrRoles.list();
	}

}
