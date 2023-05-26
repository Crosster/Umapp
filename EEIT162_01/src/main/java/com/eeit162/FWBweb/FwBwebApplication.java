package com.eeit162.FWBweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FwBwebApplication {
	public static void main(String[] args) {
		SpringApplication.run(FwBwebApplication.class, args);
	}

}


