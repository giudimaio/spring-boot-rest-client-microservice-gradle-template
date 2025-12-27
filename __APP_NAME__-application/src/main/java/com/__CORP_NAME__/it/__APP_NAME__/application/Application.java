package com.__CORP_NAME__.it.__APP_NAME__.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.__CORP_NAME__.it.__APP_NAME__"
})
@ComponentScan(basePackages = {
        "com.__CORP_NAME__.it.__APP_NAME__"
})
@EnableJpaRepositories(basePackages = {
        "com.__CORP_NAME__.it.__APP_NAME__.data"
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
