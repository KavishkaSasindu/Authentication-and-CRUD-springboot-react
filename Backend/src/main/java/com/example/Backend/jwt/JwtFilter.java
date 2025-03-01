package com.example.Backend.jwt;

import com.example.Backend.model.UserModel;
import com.example.Backend.repo.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepo userRepo;

    public JwtFilter(JwtService jwtService,UserRepo userRepo) {
        this.jwtService = jwtService;
        this.userRepo = userRepo;
    }
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenFromHeader(request);
        if(token != null) {
            String username = jwtService.getUsernameFromToken(token);
            if(username != null) {
                UserModel user = userRepo.findByUsername(username);
                if(user != null) {
                    if(SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserDetails userDetails = User.builder().
                                username(user.getUsername())
                                .password(user.getPassword())
                                .build();
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(userDetails);
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

//    get token from header
    public String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer ")) {
            token = token.split(" ")[1];
            return token;
        }
        return null;
    }
}
