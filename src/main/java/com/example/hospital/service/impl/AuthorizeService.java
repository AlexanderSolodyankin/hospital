package com.example.hospital.service.impl;



import com.example.hospital.security.JetHandler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeService {

    private final PasswordEncoder passwordEncoder;

    private final JetHandler jwtHandler;
    private final UserDetailsService userDetailsService;

    public AuthorizeService(
            PasswordEncoder passwordEncoder,
            JetHandler jwtHandler,
            UserDetailsService userDetailsService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.jwtHandler = jwtHandler;
        this.userDetailsService = userDetailsService;
    }


    public String loginUser(String userName, String userPassword) {
        UserDetails user =  this.userDetailsService.loadUserByUsername(userName);
        if(this.passwordEncoder.matches(userPassword, user.getPassword())){
            Authentication authentication =
                    UsernamePasswordAuthenticationToken.authenticated(user, null, user.getAuthorities());
            return this.jwtHandler.generateToken(authentication);
        }
        return "Неверный логин или пароль";
    }
    public String loginUser() {
        return "Bearer " + this.jwtHandler.generateToken(SecurityContextHolder.getContext().getAuthentication());
    }

}
