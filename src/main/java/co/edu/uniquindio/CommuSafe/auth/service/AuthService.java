package co.edu.uniquindio.CommuSafe.auth.service;

import co.edu.uniquindio.CommuSafe.auth.dto.AuthRequest;
import co.edu.uniquindio.CommuSafe.auth.dto.AuthResponse;
import co.edu.uniquindio.CommuSafe.auth.implementation.AuthServiceInterface;
import co.edu.uniquindio.CommuSafe.auth.model.User;
import co.edu.uniquindio.CommuSafe.auth.repository.AuthRepository;
import co.edu.uniquindio.CommuSafe.auth.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService implements AuthServiceInterface {

    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AuthResponse authenticate(AuthRequest authRequest) {
        Optional<User> user = authRepository.findByEmail(authRequest.getEmail());

        if (user.isPresent() && passwordEncoder.matches(authRequest.getPassword(), user.get().getPassword())) {
            String token = jwtUtil.generateToken(user.get().getEmail());
            return new AuthResponse(token);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect credentials");
        }
    }

    public void logout(String token) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "not implemented yet");
    }
}
