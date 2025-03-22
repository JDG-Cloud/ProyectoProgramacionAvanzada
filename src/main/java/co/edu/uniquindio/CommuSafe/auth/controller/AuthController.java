package co.edu.uniquindio.CommuSafe.auth.controller;

import co.edu.uniquindio.CommuSafe.auth.dto.AuthRequest;
import co.edu.uniquindio.CommuSafe.auth.dto.AuthResponse;
import co.edu.uniquindio.CommuSafe.auth.model.User;
import co.edu.uniquindio.CommuSafe.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        authService.register(user);
        return "user successfully registered";
    }
}
