package de.hawhamburg.textgame.security;

import de.hawhamburg.textgame.user.Role;
import de.hawhamburg.textgame.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Manages the authorization for all endpoints of this web application.
 */
@Configuration
@EnableWebSecurity
class AuthorizationConfig {

    @Bean
    SecurityFilterChain filters(HttpSecurity http) throws Exception {
        http.formLogin(Customizer.withDefaults()); // Use default login screen from Spring

        http.logout(logout -> logout.logoutSuccessUrl("/")); // Show landing page after successful logout

        http.authorizeHttpRequests(authz -> authz
            .requestMatchers("/admin").hasAuthority(Role.ADMIN) // This endpoint is only available for users with the ROLE_ADMIN.
            .requestMatchers("/secure").authenticated() // This endpoint is available for any logged-in user (regardless of the role).
            .requestMatchers("/styles.css").permitAll()
                .requestMatchers("/wouldyourather.css").permitAll()
                .requestMatchers("/results").permitAll()
                .requestMatchers("/welcome").permitAll()
                .requestMatchers("/wouldyourather").permitAll()
                .requestMatchers("/wouldyouratherPost").permitAll()// This is not an endpoint but access to other resources must be set as well. You may also use the * for multiple files, e.g., *.css or /img/*.*
            .requestMatchers("/").permitAll() // Make landing page publicly accessible
            .anyRequest().authenticated() // Secure any other page (aka blacklist)

        );

        return http.build();
    }

    /**
     * Tell Spring what {@link PasswordEncoder} to use to encrypt passwords.
     *
     * @see <a href="https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html"></a>
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Tells Spring to use our custom {@link UserRetriever} that
     * retrieves a user from the database during the login process.
     * Spring requires a {@link UserDetailsService} to find stored users.
     */
    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserRetriever(userRepository);
    }
}