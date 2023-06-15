package dev.bitan.ecommerceapp;

import dev.bitan.ecommerceapp.user.model.User;
import dev.bitan.ecommerceapp.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class EcommerceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAppApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		return args -> {
//			userRepository.save(new User("bitan1",
//					passwordEncoder.encode("password"), Collections.singletonList("ROLE_ADMIN")));
//		};
//	}
}
