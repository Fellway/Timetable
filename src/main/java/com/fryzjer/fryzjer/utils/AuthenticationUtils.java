package com.fryzjer.fryzjer.utils;

import com.fryzjer.fryzjer.exception.ForbiddenException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.fryzjer.fryzjer.exception.ApiRestError.USER_IS_NOT_AUTHENTICATED;

public class AuthenticationUtils {

    private AuthenticationUtils() {

    }

    public static String getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if(authentication.isAuthenticated()) {
                return authentication.getName();
            }
        }
        throw new ForbiddenException(USER_IS_NOT_AUTHENTICATED);
    }
}
