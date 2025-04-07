package co.edu.uniquindio.CommuSafe.modules.user.dto;

import co.edu.uniquindio.CommuSafe.modules.security.model.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationRequest {
    private String email;
    private String name;
    private String address;
    private String phone;
    private String password;
    private String urlProfile;
    private Role role;
}
