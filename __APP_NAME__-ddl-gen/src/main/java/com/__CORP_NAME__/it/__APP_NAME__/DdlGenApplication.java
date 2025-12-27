package com.__CORP_NAME__.it.___APP_NAME__;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.__CORP_NAME__.it.___APP_NAME__"})
public class DdlGenApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdlGenApplication.class, args);
    }

}
