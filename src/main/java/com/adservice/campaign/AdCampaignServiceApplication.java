package com.adservice.campaign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdCampaignServiceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdCampaignServiceApplication.class);
	
	public static void main(String[] args) {
		LOGGER.trace("Starting Ad Campaign Server...");
		SpringApplication.run(AdCampaignServiceApplication.class, args);
	}
	

}
