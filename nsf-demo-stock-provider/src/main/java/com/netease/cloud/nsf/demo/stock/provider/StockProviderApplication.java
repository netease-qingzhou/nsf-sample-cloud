package com.netease.cloud.nsf.demo.stock.provider;

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
public class StockProviderApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(StockProviderApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StockProviderApplication.class);
	}
}
