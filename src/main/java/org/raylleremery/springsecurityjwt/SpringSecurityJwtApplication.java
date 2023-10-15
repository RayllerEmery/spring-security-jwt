package org.raylleremery.springsecurityjwt;

import org.raylleremery.springsecurityjwt.application.request.AuthenticationRequest;
import org.raylleremery.springsecurityjwt.application.request.RegisterRequest;
import org.raylleremery.springsecurityjwt.application.response.LoginResponse;
import org.raylleremery.springsecurityjwt.config.TokenUtil;
import org.raylleremery.springsecurityjwt.domain.User;
import org.raylleremery.springsecurityjwt.domain.UserRole;
import org.raylleremery.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	@Bean
	public CommandLineRunner createLogin(UserRepository userRepository) {
		return args -> {
			var encriptedPassword = new BCryptPasswordEncoder().encode("admin");
			var user = new User("admin", encriptedPassword, UserRole.ADMIN);
			userRepository.save(user);
		};
	}
}

