package com.example.hospital.service.impl;

import com.example.hospital.dto.user.request.UserRequestDto;
import com.example.hospital.exceptions.users_related_exception.UserAuthenticationException;
import com.example.hospital.security.JwtHandler;
import com.example.hospital.service.AuthorizeService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    private final PasswordEncoder passwordEncoder;
    private final JwtHandler jwtHandler;
    private final UserDetailsService userDetailsService;

    public AuthorizeServiceImpl(
            PasswordEncoder passwordEncoder,
            JwtHandler jwtHandler,
            UserDetailsService userDetailsService
    ) {
        this.passwordEncoder = passwordEncoder;
        this.jwtHandler = jwtHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String logInUser(UserRequestDto requestDto) {
        UserDetails user = this.userDetailsService.loadUserByUsername(requestDto.getLogin());
        if (this.passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            Authentication authentication =
                    UsernamePasswordAuthenticationToken.authenticated(user, null, user.getAuthorities());
            return "Bearer: " + this.jwtHandler.generationToken(authentication);
        }
        throw new UserAuthenticationException("Неверный логин или пароль");
    }

    @Override
    public String logInUser() {
        return "Bearer: " + this.jwtHandler
                .generationToken(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                );
    }

    @Override
    public boolean passwordValidats(String passwordEntryEndPoint, String passwordHandlesFromSystem) {
        return passwordEncoder.matches(passwordEntryEndPoint, passwordHandlesFromSystem);
    }
    @Override
    public boolean passwordValidats(String passwordEntryEndPoint) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (passwordEncoder.matches(passwordEntryEndPoint, user.getPassword())){
           return true;
        }
         throw new UserAuthenticationException("Пароли не совпадают");
    }

    @Override
    public String passwordEncode(String password){
        return passwordEncoder.encode(password);
    }
}
