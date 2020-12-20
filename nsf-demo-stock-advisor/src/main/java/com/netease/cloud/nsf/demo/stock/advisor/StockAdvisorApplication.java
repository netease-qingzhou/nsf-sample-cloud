package com.netease.cloud.nsf.demo.stock.advisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chenjiahan
 */
@SpringBootApplication
@EnableEurekaClient
public class StockAdvisorApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(StockAdvisorApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StockAdvisorApplication.class);
	}
}

