package com.cloudycat.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by spandhare on 7/5/16.
 */

/**
 * Filter chain that intercepts and checks for authentication header
 */
public class XAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTH_HEADER = "X-Auth-Key".toLowerCase();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        Enumeration<String> headerNames = request.getHeaderNames();
        boolean is_auth_present = false;

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            //System.out.println(headerName);
            if (headerName.equals(AUTH_HEADER)) {
                is_auth_present =  true;
                break;
            }
        }
        final String xAuth;
        if (!is_auth_present) {
            //Auth header absent
            xAuth = "";
        } else {
            xAuth = request.getHeader(AUTH_HEADER);
        }

        // Create our Authentication and let Spring know about it
        Authentication auth = new XAuthToken(xAuth);
        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

}
