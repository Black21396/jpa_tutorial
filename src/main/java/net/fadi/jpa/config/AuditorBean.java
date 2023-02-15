package net.fadi.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


// this class to defeine our Auditor als Bean to tell application context to create a bean in container
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@Configuration
public class AuditorBean {

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAwareImpl();
    }

}
