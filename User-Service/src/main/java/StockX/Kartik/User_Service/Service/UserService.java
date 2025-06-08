package StockX.Kartik.User_Service.Service;

import StockX.Kartik.User_Service.DataAccessObject.UserDao;
import StockX.Kartik.User_Service.DataTransferObject.RegisterRequest;
import StockX.Kartik.User_Service.DataTransferObject.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired private PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if (userDao.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already taken");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userDao.save(user);
    }

    public User getByUsername(String username) {
        return userDao.findByUsername(username).orElseThrow();
    }

    public List<User> getUsers() {

        return userDao.findAll();
    }
}
