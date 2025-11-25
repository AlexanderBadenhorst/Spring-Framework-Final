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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Allow frame access for H2 console
                .headers(headers -> headers
                        .frameOptions().disable()
                )

                // Disable CSRF only for H2 console
                .csrf(csrf -> csrf
                        .ignoringAntMatchers("/h2-console/**")
                )

                .authorizeHttpRequests(auth -> auth
                        // Allow H2 console without authentication
                        .antMatchers("/h2-console/**").permitAll()

                        // Secure design + orders
                        .mvcMatchers("/design", "/orders").hasRole("USER")

                        // Everything else
                        .mvcMatchers("/", "/**").permitAll()
                )

                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/design", true)
                )
                .logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();
    }
}
