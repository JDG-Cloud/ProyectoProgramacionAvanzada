package co.edu.uniquindio.CommuSafe.user.service;

import co.edu.uniquindio.CommuSafe.user.implementation.UserServiceInterface;
import co.edu.uniquindio.CommuSafe.user.dto.UserCreationRequest;
import co.edu.uniquindio.CommuSafe.user.dto.UserCreationResponse;
import co.edu.uniquindio.CommuSafe.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserCreationResponse create(UserCreationRequest userCreationRequest) {
        return null;
    }
}
