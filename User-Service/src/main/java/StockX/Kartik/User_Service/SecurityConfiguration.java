package StockX.Kartik.User_Service;

import StockX.Kartik.User_Service.Filters.JwtFilter;
import StockX.Kartik.User_Service.Service.JwtUtil;
import StockX.Kartik.User_Service.Service.UserDetailsAuthenticationService;
import StockX.Kartik.User_Service.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// === Security Configuration ===
@Configuration
@EnableWebSecurity(debug = true)
class SecurityConfiguration{

    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserDetailsAuthenticationService authenticationService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf ->csrf.disable())
                .authorizeHttpRequests(request -> request.requestMatchers(SecurityConstants.PUBLIC_URLS).permitAll()
                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtFilter(jwtUtil, authenticationService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
