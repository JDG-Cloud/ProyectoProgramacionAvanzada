package co.edu.uniquindio.CommuSafe.modules.user.model;

import co.edu.uniquindio.CommuSafe.modules.security.model.Otp;
import co.edu.uniquindio.CommuSafe.modules.security.model.Role;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.List;

@Document(collection = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    private String id;  // 🔹 Cambiado de "uuid" a "id"

    private String email;
    private String name;
    private String address;
    private String phone;
    private String password;
    private Role role;
    private List<Otp> otps;
    private Status status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getTitle()));
    }

    @Override
    public String getUsername() {
        return this.email;  // 🔹 Se usa email en vez de name
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
