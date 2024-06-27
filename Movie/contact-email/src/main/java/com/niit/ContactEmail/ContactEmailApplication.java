package com.niit.ContactEmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ContactEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactEmailApplication.class, args);
		System.out.println("====== Contact Email Application ======");
	}
}
