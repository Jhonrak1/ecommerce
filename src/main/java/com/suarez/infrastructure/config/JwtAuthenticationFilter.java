package com.suarez.infrastructure.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
//class JwtAuthenticationFilter extends OncePerRequestFilter {
public class JwtAuthenticationFilter {

//    private final UserDetailsService userDetailsService;

//    @Value("${jwt.secret}")
//    private String jwtSecret;

//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String username;
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        jwt = authHeader.substring(7);
//        try {
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
//                    .build()
//                    .parseClaimsJws(jwt)
//                    .getBody();
//
//            username = claims.getSubject();
//
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//                if (isTokenValid(jwt, userDetails)) {
//                    var authToken = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    SecurityContextHolder.getContext().setAuthentication(authToken);
//                }
//            }
//        } catch (Exception e) {
//            // Token validation failed
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private boolean isTokenValid(String token, UserDetails userDetails) {
//        try {
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody();
//
//            final String username = claims.getSubject();
//            final Date expiration = claims.getExpiration();
//
//            return username.equals(userDetails.getUsername()) && !expiration.before(new Date());
//        } catch (Exception e) {
//            return false;
//        }
//    }
}
