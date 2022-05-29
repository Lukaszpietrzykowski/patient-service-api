package com.sop;

import com.sop.creators.UserCreator;
import com.sop.enums.RoleEnum;
import com.sop.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PatientServiceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApiApplication.class, args);
    }
}

