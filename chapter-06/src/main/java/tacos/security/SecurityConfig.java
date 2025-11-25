package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests(auth -> auth
                        // Public pages
                        .antMatchers("/", "/register", "/login",
                                "/images/**", "/styles/**").permitAll()

                        // Require login for designing tacos
                        .antMatchers("/design").hasRole("USER")

                        // Require login for placing orders
                        .antMatchers("/orders/**").hasRole("USER")

                        // Allow H2 console
                        .antMatchers("/h2-console/**").permitAll()

                        // Everything else requires authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/design", true)
                )
                .logout(logout -> logout.logoutSuccessUrl("/"))

                // Disable CSRF for H2 + easy testing
                .csrf(csrf -> csrf.disable())

                // Allow H2 console frames
                .headers(headers -> headers.frameOptions().sameOrigin());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
