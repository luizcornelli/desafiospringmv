package com.desafiomvspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafiomvspringApplication {
	
	private static Logger logger = LoggerFactory.getLogger(DesafiomvspringApplication.class);
	
	public static void main(String[] args) {
		
		logger.info("##### Iniciando API Desafio Spring Sistemas-MV ###########");
		
		SpringApplication.run(DesafiomvspringApplication.class, args);
		
		logger.info("##### API iniciada e pronta para receber requisições #####");
	}
	
}
