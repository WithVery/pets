package group.cards.controllers;

import group.cards.domain.model.UserEntity;
import group.cards.repos.UserRepo;
import group.cards.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  private AuthenticationManager authenticationManager;
  private UserRepo userRepo;
  private PasswordEncoder passwordEncoder;
  private JwtUtil jwtUtil;

  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager, UserRepo userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
  }

  @PostMapping("/signin")
  public String authenticateUser(@RequestBody UserEntity user) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
        user.getUsername(),
        user.getPassword()
        )
    );

    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    return jwtUtil.generateToken(userDetails.getUsername());
  }

}
