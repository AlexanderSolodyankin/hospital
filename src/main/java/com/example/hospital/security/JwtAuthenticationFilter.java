package com.example.hospital.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JetHandler jwtHandler;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthenticationFilter(
            JetHandler jwtHandler,
            UserDetailsService userDetailsService) {
        this.jwtHandler = jwtHandler;

        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = this.parseJwt(request);

        if(Objects.nonNull(token) && this.jwtHandler.validationToken(token)){
            String userName = this.jwtHandler.getUserNameFromToken(token);
            UserDetails user =this.userDetailsService.loadUserByUsername(userName);

            UsernamePasswordAuthenticationToken authToken =
                    UsernamePasswordAuthenticationToken.authenticated(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);


    }

    private String parseJwt(HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        if(Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }
        return null;
    }
}
