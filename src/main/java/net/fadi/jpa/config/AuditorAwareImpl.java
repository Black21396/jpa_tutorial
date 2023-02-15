package net.fadi.jpa.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

// in this class get the user info to put in to store it in database
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // here we have to write the code to get user info from DB and put it here, but for test we put fixed name
        return Optional.of("fadi");
    }
}
