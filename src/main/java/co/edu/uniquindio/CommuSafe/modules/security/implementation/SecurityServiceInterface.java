package co.edu.uniquindio.CommuSafe.modules.security.implementation;
import co.edu.uniquindio.CommuSafe.modules.security.dto.AuthRequest;
import co.edu.uniquindio.CommuSafe.modules.security.dto.AuthResponse;

public interface SecurityServiceInterface {
    public AuthResponse authenticate(AuthRequest authRequest);
    public void logout(String token);
}
