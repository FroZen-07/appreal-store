package dev.bitan.ecommerceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EcommerceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceAppApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		return args -> {
//			userRepository.save(new User("bitan",
//					passwordEncoder.encode("password"), "ROLE_USER"));
//			userRepository.save(new User("bitan1",
//					passwordEncoder.encode("password"), "ROLE_ADMIN"));
//		};
//	}
}
