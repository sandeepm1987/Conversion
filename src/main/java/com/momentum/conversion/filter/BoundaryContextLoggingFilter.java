package com.momentum.conversion.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class BoundaryContextLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Starting request : {}", request.getRequestURI());
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(request,response);
        long endTime = System.currentTimeMillis() - startTime;
        log.info("Ending request   : {}, Total time taken : {}", request.getRequestURI(), endTime);
    }
}
