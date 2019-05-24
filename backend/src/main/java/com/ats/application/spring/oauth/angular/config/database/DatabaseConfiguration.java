package com.ats.application.spring.oauth.angular.config.database;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

	@Autowired
	private Environment environment;

	@Autowired
	public DataSource datasource;

	@Bean
	public LocalSessionFactoryBean sessionFactory() throws NamingException {
		LocalSessionFactoryBean sessionFactory = null;
		Properties hibernateProp = null;
		sessionFactory = new LocalSessionFactoryBean();
		hibernateProp = new Properties();
		sessionFactory.setDataSource(datasource);
		sessionFactory.setPackagesToScan(new String[] { "com.ats.applications" });
		hibernateProp.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		hibernateProp.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		sessionFactory.setHibernateProperties(hibernateProp);
		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() throws NamingException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}
