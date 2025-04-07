package co.edu.uniquindio.CommuSafe.modules.user.dto;

import co.edu.uniquindio.CommuSafe.modules.security.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserModificationRequest {
    private String name;
    private String address;
    private String phone;
    private String password;
    private String email;
    private Role role;
}
