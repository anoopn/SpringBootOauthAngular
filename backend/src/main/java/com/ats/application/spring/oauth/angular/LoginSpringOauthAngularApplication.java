package com.ats.application.spring.oauth.angular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ats")
public class LoginSpringOauthAngularApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LoginSpringOauthAngularApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LoginSpringOauthAngularApplication.class);
	}

}
