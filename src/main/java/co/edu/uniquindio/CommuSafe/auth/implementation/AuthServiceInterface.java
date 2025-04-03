package co.edu.uniquindio.CommuSafe.auth.implementation;
import co.edu.uniquindio.CommuSafe.auth.dto.AuthRequest;
import co.edu.uniquindio.CommuSafe.auth.dto.AuthResponse;

public interface AuthServiceInterface {
    public AuthResponse authenticate(AuthRequest authRequest);
    public void logout(String token);
}
