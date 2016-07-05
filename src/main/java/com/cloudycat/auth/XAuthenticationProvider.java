package com.cloudycat.auth;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by spandhare on 7/5/16.
 */

/**
 *
 * AUthentication mechanism used by Spring security
 */

@Component
public class XAuthenticationProvider implements AuthenticationProvider {

    FileReadTokensService fileReadTokensService = new FileReadTokensService();

    private static String FILE_PATH = "/tokens/input.properties";

    public XAuthenticationProvider() {
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        XAuthToken xAuthenticationTokenuthentication = (XAuthToken) authentication;

        if (xAuthenticationTokenuthentication.getPrincipal() == null ||
                xAuthenticationTokenuthentication.getPrincipal().isEmpty()) {
            throw new BadCredentialsException(
                    "Incorrect Authenticated token header X-Auth-Key in request.");
        }

        //List<String> tokens = fileReadTokensService.readTokensAndReturnList(FILE_PATH);
        List<String> tokens = fileReadTokensService.readTokensUsingStream(FILE_PATH);
        if (tokens == null || tokens.isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Cannot read tokens, cannot authenticate");
        }

        //Currently just one token, so looking for just first one
        String persistedToken = tokens.get(0);
        String userToken = xAuthenticationTokenuthentication.getPrincipal();
        boolean check = false;
        try {
            check = PasswordHash.validatePassword(userToken.toCharArray(), persistedToken);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        if (!check) {
            throw new BadCredentialsException(
                    "Incorrect token.");
        }

        return xAuthenticationTokenuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return XAuthToken.class.isAssignableFrom(authentication);
    }

}
