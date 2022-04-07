package com.personal.cl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Hooks;

/**
 * @author xiaowenrou
 */
@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		Hooks.onOperatorDebug();
		SpringApplication.run(StartApplication.class, args);
	}

}
