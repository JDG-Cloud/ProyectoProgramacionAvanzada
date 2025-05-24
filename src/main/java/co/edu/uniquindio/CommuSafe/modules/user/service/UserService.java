package co.edu.uniquindio.CommuSafe.modules.user.service;

import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationResponse;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserModificationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserResponse;
import co.edu.uniquindio.CommuSafe.modules.user.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserCreationResponse create(UserCreationRequest userCreationRequest);
    UserResponse update(UserModificationRequest userModificationRequest);
    UserResponse uploadUserProfileImage(MultipartFile file, String userId);
    UserResponse delete(UserModificationRequest userModificationRequest);
    User getUserByEmail(String email);
}
