package com.roche.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories({"com.roche.store.core.repository"})
@EntityScan({ "com.roche.store.core.domain"})
@EnableTransactionManagement
public class StoreApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

}
