package com.burundibuhire.burundi.buhire.filter;

import java.io.IOException;

import com.burundibuhire.burundi.buhire.services.auth.UserInfoService;
import com.burundibuhire.burundi.buhire.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserInfoService userInfoService;

    @Autowired
    public JwtFilter(JwtUtils jwtUtils, UserInfoService userInfoService) {
        this.jwtUtils = jwtUtils;
        this.userInfoService = userInfoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);

            userName = jwtUtils.extractUsername(token);

            System.out.println("Extracted Token: " + userName);
        }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
            //UserDetails userDetails = userInfoService.loadUserByUsername(userName);

            try {
                UserDetails userDetails = userInfoService.loadUserByUsername(userName);
                if (jwtUtils.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (UsernameNotFoundException e) {
                System.out.println("User not found: " + userName);
            }

        }

        filterChain.doFilter(request, response);

    }

}
