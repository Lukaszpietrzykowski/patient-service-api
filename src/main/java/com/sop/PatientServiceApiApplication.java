package com.sop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class PatientServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApiApplication.class, args);
	}

}
