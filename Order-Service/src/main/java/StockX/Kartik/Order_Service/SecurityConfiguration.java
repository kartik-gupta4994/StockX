package StockX.Kartik.Order_Service;

import StockX.Authorization.JwtFilter;
import StockX.Authorization.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// === Security Configuration ===
@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, JwtTokenUtil jwtUtil) throws Exception {
        http.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/ws/**","/topic/**").permitAll()
                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtTokenUtil jwtUtil(@Value("${jwt.secret}") String secret,
                                @Value("${jwt.expiration}") long expiration) {
        return new JwtTokenUtil(secret, expiration);
    }
}
