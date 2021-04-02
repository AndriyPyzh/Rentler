package com.rentler.auth.filter;

import com.google.common.base.Strings;
import com.rentler.auth.util.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final Jwt jwt;

    public JwtTokenFilter(Jwt jwt) {
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getTokenFromHttpServletRequest(request);

        if (Strings.isNullOrEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {

            String username = getUsernameFromToken(token);

            List<String> authorities = getAuthoritiesFromToken(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHttpServletRequest(HttpServletRequest servletRequest) {
        return Optional
                .ofNullable(servletRequest.getHeader("Authorization"))
                .filter(authHeader -> authHeader.startsWith("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    private String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwt.getAccessTokenKey())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private List<String> getAuthoritiesFromToken(String token) {
        return (List<String>) Jwts.parser()
                .setSigningKey(jwt.getAccessTokenKey())
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }

}
