package co.edu.uniquindio.CommuSafe.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserCreationRequest {
    private String email;
    private String password;
}
