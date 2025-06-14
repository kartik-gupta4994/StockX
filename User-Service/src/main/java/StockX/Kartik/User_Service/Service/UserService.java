package StockX.Kartik.User_Service.Service;

import StockX.Kartik.User_Service.DataAccessObject.UserDao;
import StockX.Kartik.User_Service.DataTransferObject.User;
import StockX.Kartik.User_Service.DataTransferObject.UserProfileRequest;
import StockX.Kartik.User_Service.DataTransferObject.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserProfileResponse getProfile(String username) {

        Optional<User> optionalUser = Optional.of(userDao.findByUsername(username).orElseThrow());

        User user = optionalUser.get();
        return new UserProfileResponse(
                user.getUsername(),
                user.getEmail(),
                user.getWalletBalance()
        );
    }

    public void createProfile(UserProfileRequest userProfileRequest) {
        User profile = new User();
        profile.setEmail(userProfileRequest.getEmail());
        profile.setName(userProfileRequest.getName());
        profile.setUsername(userProfileRequest.getUsername());
        userDao.save(profile);
    }
}
