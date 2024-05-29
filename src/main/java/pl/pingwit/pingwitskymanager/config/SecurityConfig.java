package pl.pingwit.pingwitskymanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static pl.pingwit.pingwitskymanager.controller.UrlUtils.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";

    @Bean
    public UserDetailsService users() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        UserDetails user = users
                .username("user1")
                .password("user1")
                .roles(USER_ROLE)
                .build();

        UserDetails user2 = users
                .username("user2")
                .password("user2")
                .build();

        UserDetails admin = users
                .username("admin")
                .password("admin")
                .roles(ADMIN_ROLE, USER_ROLE)
                .build();
        return new InMemoryUserDetailsManager(user, admin, user2);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(withDefaults())
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.POST, AIRCRAFT_URL, AIRCRAFT_MODEL_URL, CREW_URL,
                                DIRECTION_URL, EMPLOYEE_URL, FLIGHT_URL).hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.DELETE, AIRCRAFT_URL, AIRCRAFT_MODEL_URL, CREW_URL,
                                DIRECTION_URL, EMPLOYEE_URL, FLIGHT_URL).hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.GET, EMPLOYEE_URL).hasRole(ADMIN_ROLE)
                        .requestMatchers(HttpMethod.GET, AIRCRAFT_URL, AIRCRAFT_MODEL_URL + "/**",
                                AIRCRAFT_MODEL_URL, AIRCRAFT_MODEL_URL + "/**", CREW_URL, CREW_URL + "**",
                                DIRECTION_URL, DIRECTION_URL + "/**", FLIGHT_URL, FLIGHT_URL + "/**")
                        .hasAnyRole(ADMIN_ROLE, USER_ROLE)
                        .requestMatchers("/swagger-ui", "/swagger-ui/**", "/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
