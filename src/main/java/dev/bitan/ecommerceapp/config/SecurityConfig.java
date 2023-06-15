package dev.bitan.ecommerceapp.config;

import dev.bitan.ecommerceapp.user.service.MongoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final MongoUserDetailsService mongoUserDetailsService;

    public SecurityConfig(MongoUserDetailsService mongoUserDetailsService) {
        this.mongoUserDetailsService = mongoUserDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails normalUser = User
//                .withUsername("bitan")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails adminUser = User
//                .withUsername("bitan1")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)
            throws Exception {

        httpSecurity.authorizeHttpRequests(auth -> auth

                        .anyRequest().authenticated())
                .userDetailsService(mongoUserDetailsService) // Fetches data from db
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();

    }

}