package com.xebia.automating_flyers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutomatingFlyersApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomatingFlyersApplication.class, args);
	}

}
