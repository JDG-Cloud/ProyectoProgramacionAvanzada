package co.edu.uniquindio.CommuSafe.modules.user.implementation;

import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserCreationResponse;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserModificationRequest;
import co.edu.uniquindio.CommuSafe.modules.user.dto.UserModificationResponse;

public interface UserServiceInterface {
    public UserCreationResponse create(UserCreationRequest userCreationRequest);

    public UserModificationResponse update(UserModificationRequest userModificationRequest);
}
