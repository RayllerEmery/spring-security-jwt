package org.raylleremery.springsecurityjwt;

import org.raylleremery.springsecurityjwt.domain.User;
import org.raylleremery.springsecurityjwt.domain.UserRole;
import org.raylleremery.springsecurityjwt.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

