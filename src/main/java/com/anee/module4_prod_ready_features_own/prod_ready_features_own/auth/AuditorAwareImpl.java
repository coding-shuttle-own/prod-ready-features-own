package com.anee.module4_prod_ready_features_own.prod_ready_features_own.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // get the security context
        // get authentication
        // get the principal
        // get the username;
        return Optional.of("Aneervan Ray");
    }
}
