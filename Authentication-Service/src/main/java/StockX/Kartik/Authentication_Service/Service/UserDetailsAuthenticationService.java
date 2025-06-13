package StockX.Kartik.Authentication_Service.Service;

import StockX.Kartik.Authentication_Service.DataTransfer.UserIdentity;
import StockX.Kartik.Authentication_Service.DataTransfer.UserIdentityPrinciple;
import StockX.Kartik.Authentication_Service.Repository.UserAuthRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

//Must require so Spring can identify from where to load the data for authentication
@Service
public class UserDetailsAuthenticationService implements UserDetailsService {

    @Autowired
    private UserAuthRepo userAuthRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserIdentity> user = userAuthRepo.findByUsername(username);

        return UserIdentityPrinciple.fromUser(user.get());
    }
}
