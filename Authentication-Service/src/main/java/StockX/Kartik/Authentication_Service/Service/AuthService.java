package StockX.Kartik.Authentication_Service.Service;

import StockX.Authorization.JwtTokenUtil;
import StockX.Kartik.Authentication_Service.DataTransfer.*;
import StockX.Kartik.Authentication_Service.Repository.UserAuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authManager;
    private final UserServiceClient userServiceClient;

    public void register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username already taken");
        }

        UserIdentity user = new UserIdentity();
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);

        //For User Service Data Saving
        UserProfileRequest profile = new UserProfileRequest();
        profile.setUsername(req.getUsername());
        profile.setName(req.getName());
        profile.setEmail(req.getEmail());

        userServiceClient.createProfile(profile);

    }

    public AuthResponse login(LoginRequest request) {
        System.out.println("inside login service");
        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        UserIdentityPrinciple principle = (UserIdentityPrinciple) auth.getPrincipal();
        String token = jwtTokenUtil.generateToken(principle.getId(), principle.getUsername());
        return new AuthResponse(token);
    }

    public List<UserIdentity> getUsers() {

        return userRepository.findAll();
    }
}

