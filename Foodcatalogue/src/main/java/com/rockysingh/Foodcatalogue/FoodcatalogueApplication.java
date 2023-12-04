package com.rockysingh.Foodcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FoodcatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodcatalogueApplication.class, args);
	}



	/*
	* We use the @LoadBalanced to tell spring which instance of the restaurantListing to fetch the data from
	*
	* */

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
