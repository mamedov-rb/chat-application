package com.rb.alwaysontheroad.chatservice.config;

import com.rb.alwaysontheroad.chatservice.shared.event.Routes;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Profile("!integration-test")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String V_3_API_DOCS = "/v3/api-docs/**";
    public static final String WEBJARS_SWAGGER_UI = "/webjars/swagger-ui/**";
    public static final String SWAGGER_UI_HTML = "/swagger-ui.html";
    public static final String SWAGGER_UI = "/swagger-ui/**";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(grantedAuthoritiesExtractor());

        http.authorizeRequests(registry -> {
            registry.mvcMatchers("/actuator/**").permitAll();
            registry.mvcMatchers(V_3_API_DOCS, SWAGGER_UI, WEBJARS_SWAGGER_UI, SWAGGER_UI_HTML).permitAll();
            registry.anyRequest().authenticated();
        });

        http.csrf().disable();
    }

    private Converter<Jwt, AbstractAuthenticationToken> grantedAuthoritiesExtractor() {
        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtAuthenticationConverter;
    }

    private static class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        @Override
        public Collection<GrantedAuthority> convert(final Jwt jwt) {
            final Map<String, Object> claims = jwt.getClaims();
            final Map<String, Object> realmAccessMap = (Map<String, Object>) claims.get("realm_access");
            return ((List<String>) realmAccessMap.get("roles")).stream()
                    .map(roleName -> "ROLE_" + roleName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
    }
}
