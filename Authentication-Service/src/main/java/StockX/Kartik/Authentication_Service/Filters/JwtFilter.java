package StockX.Kartik.Authentication_Service.Filters;

import StockX.Kartik.Authentication_Service.Service.JwtTokenUtil;
import StockX.Kartik.Authentication_Service.SecurityConstants;
import StockX.Kartik.Authentication_Service.Service.UserDetailsAuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

// Adding JWT filter in the filter chain
public class JwtFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtUtil;
    private final UserDetailsAuthenticationService authenticationService;

    public JwtFilter(JwtTokenUtil jwtUtil, UserDetailsAuthenticationService authenticationService) {
        this.jwtUtil = jwtUtil;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // Skip token check for public endpoints
        if (Arrays.asList(SecurityConstants.PUBLIC_URLS).contains(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String username = jwtUtil.extractUsername(token);
            UserDetails user = authenticationService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}
