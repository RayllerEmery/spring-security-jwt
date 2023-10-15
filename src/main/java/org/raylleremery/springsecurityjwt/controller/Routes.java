package org.raylleremery.springsecurityjwt.controller;

import org.raylleremery.springsecurityjwt.application.request.AuthenticationRequest;
import org.raylleremery.springsecurityjwt.application.request.RegisterRequest;
import org.raylleremery.springsecurityjwt.application.response.LoginResponse;
import org.raylleremery.springsecurityjwt.config.TokenUtil;
import org.raylleremery.springsecurityjwt.domain.User;
import org.raylleremery.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Routes {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenUtil tokenService;

    @GetMapping("/public")
    String publicRoute() {
        return "<h1>This is a unlocked route! :unlock: <h1>";
    }

    @GetMapping("/private")
    String privateRoute() {
        return "<h1> This is a lock route! :lock: <h1>";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationRequest.login(), authenticationRequest.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        if(this.repository.findByLogin(registerRequest.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.password());
        User newUser = new User(registerRequest.login(), encryptedPassword, registerRequest.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
