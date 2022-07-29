package com.example.cjv_assignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CjvAssignment2Application {

	public static void main(String[] args) {
		SpringApplication.run(CjvAssignment2Application.class, args);
	}

//	Do not put a lot of logic in the controller, the controller should not be too big
//	controller is used to communicate with the client, the routes are created in the controller.
//	service and model layer is used to communicate with the database

}
