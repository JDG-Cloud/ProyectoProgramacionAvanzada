package co.edu.uniquindio.CommuSafe.modules.security.service;

import co.edu.uniquindio.CommuSafe.modules.security.dto.AuthRequest;
import co.edu.uniquindio.CommuSafe.modules.security.dto.AuthResponse;

public interface SecurityService {
    public AuthResponse authenticate(AuthRequest authRequest);
    public void logout(String token);
}
