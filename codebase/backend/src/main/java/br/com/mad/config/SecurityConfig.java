package br.com.mad.config;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class SecurityConfig {

    private static final RequestMatcher OPEN_API_PATHS = new OrRequestMatcher(
            PathPatternRequestMatcher.withDefaults().matcher("/v3/api-docs/**"),
            PathPatternRequestMatcher.withDefaults().matcher("/swagger-ui.html"),
            PathPatternRequestMatcher.withDefaults().matcher("/swagger-ui/**"));

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, Environment environment) throws Exception {
        boolean developmentEnvironment = Set.of(environment.getActiveProfiles()).equals(Set.of("development"));

        return http
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/api/v1/system/status", "/actuator/health").permitAll();

                    if (developmentEnvironment) {
                        authorize.requestMatchers(OPEN_API_PATHS).permitAll();
                    } else {
                        authorize.requestMatchers(OPEN_API_PATHS).denyAll();
                    }

                    authorize.anyRequest().authenticated();
                })
                .exceptionHandling(exceptions -> {
                    if (!developmentEnvironment) {
                        exceptions.defaultAuthenticationEntryPointFor(
                                new HttpStatusEntryPoint(HttpStatus.NOT_FOUND), OPEN_API_PATHS);

                        exceptions.defaultAccessDeniedHandlerFor(
                                (request, response, exception) -> response.setStatus(HttpStatus.NOT_FOUND.value()),
                                OPEN_API_PATHS);
                    }
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
