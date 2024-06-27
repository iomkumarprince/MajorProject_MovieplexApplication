package com.MovieApp.Movie.App.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MovieFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpServletRequest.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Token is missing or invalid");
        } else {
            String token = authHeader.substring(7); // Remove "Bearer " prefix
            Claims claims = Jwts.parser().setSigningKey("secretKeyForMovieApp").parseClaimsJws(token).getBody();
            System.out.println("Claims: " + claims);
            httpServletRequest.setAttribute("email", claims.get("email"));
            httpServletRequest.setAttribute("password", claims.get("password"));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
