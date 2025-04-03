package co.edu.uniquindio.CommuSafe.modules.security.controller;

import co.edu.uniquindio.CommuSafe.modules.security.implementation.SecurityServiceInterface;
import co.edu.uniquindio.CommuSafe.modules.security.dto.AuthRequest;
import co.edu.uniquindio.CommuSafe.modules.security.dto.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private final SecurityServiceInterface _authService;

    public SecurityController(SecurityServiceInterface authService) {
        _authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

        var authResponse= _authService.authenticate(authRequest);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        _authService.logout(token);
        return ResponseEntity.ok("logged out successfully");
    }
}
