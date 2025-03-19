package co.edu.uniquindio.CommuSafe.auth.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    private String uuid;
    private String email;
    private String name;
    private String address;
    private String phone;
    private String password;
    private String urlProfile;
    private Role role;
    private List<Otp> otps;
}
