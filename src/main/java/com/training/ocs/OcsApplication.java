package com.training.ocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OcsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		System.out.println("=================="+System.getProperty("pageContext.request.contextPath"));
		SpringApplication.run(OcsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return super.configure(builder);
	}
}
