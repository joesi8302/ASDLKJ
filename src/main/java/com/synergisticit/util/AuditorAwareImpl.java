package com.synergisticit.util;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth==null || !auth.isAuthenticated()) {
            return null;
        }

        if (auth.getPrincipal() instanceof UserDetails) {
            // If the principal is a UserDetails instance, return the username
            return Optional.of(((UserDetails) auth.getPrincipal()).getUsername());
        } else if (auth.getPrincipal() instanceof String) {

            return Optional.of((String) auth.getPrincipal());
        }

        return Optional.empty();

//        String user = ((UserDetails)auth.getPrincipal()).getUsername();

//        return Optional.of(user);


    }
}
