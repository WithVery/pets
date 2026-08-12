package group.cards.service.impl;

import group.cards.domain.model.UserEntity;
import group.cards.domain.model.UserRole;
import group.cards.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepo userRepo;

    @Autowired
    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.isEmpty()) {
            throw new UsernameNotFoundException("Incorrect or no user submitted!");
        }

        UserEntity user = userRepo.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        UserDetails newUser = User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole() == UserRole.ADMIN ? "ADMIN" : "USER")
            .build();

        return newUser;
    }

    public static UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
