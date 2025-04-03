package co.edu.uniquindio.CommuSafe.auth.controller;

import co.edu.uniquindio.CommuSafe.auth.implementation.AuthServiceInterface;
import co.edu.uniquindio.CommuSafe.auth.dto.AuthRequest;
import co.edu.uniquindio.CommuSafe.auth.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceInterface _authService;

    public AuthController(AuthServiceInterface authService) {
        _authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return _authService.authenticate(authRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        _authService.logout(token);
        return ResponseEntity.ok("logged out successfully");
    }
}
