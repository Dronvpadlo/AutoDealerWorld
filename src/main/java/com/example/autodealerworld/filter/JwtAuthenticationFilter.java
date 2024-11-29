package com.example.autodealerworld.filter;

import com.example.autodealerworld.services.SecurityUserService;
import com.example.autodealerworld.util.JwtUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER_VALUE_PREFIX = "Bearer ";

    private final JwtUtil jwtUtil;

    private final SecurityUserService securityUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeaderValue == null || !authHeaderValue.startsWith(AUTHORIZATION_HEADER_VALUE_PREFIX)){
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeaderValue.substring(AUTHORIZATION_HEADER_VALUE_PREFIX.length());

        if (jwtUtil.isTokenExpired(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        String username = jwtUtil.extractUsername(token);
        if (StringUtils.isNotBlank(username)){
            UserDetails userDetails = securityUserService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}