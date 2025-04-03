package co.edu.uniquindio.CommuSafe.user.implementation;

import co.edu.uniquindio.CommuSafe.user.dto.UserCreationResponse;

public interface UserServiceInterface {
    public UserCreationResponse create(co.edu.uniquindio.CommuSafe.user.dto.UserCreationRequest userCreationRequest);
}
