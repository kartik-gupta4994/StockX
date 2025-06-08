package StockX.Kartik.User_Service.Service;


import StockX.Kartik.User_Service.DataAccessObject.UserDao;
import StockX.Kartik.User_Service.DataTransferObject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

//Must require so Spring can identify from where to load the data for authentication
@Service
public class UserDetailsAuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.emptyList() // No roles for now
        );
    }
}
